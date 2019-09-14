package com.delavar.digipay.presentation.di

import android.app.Application
import android.content.SharedPreferences
import com.delavar.digipay.data.ErrorUtils
import com.delavar.digipay.data.restful.ApiService
import com.delavar.digipay.data.source.cloud.CloudSource
import com.delavar.digipay.data.source.cloud.CloudSourceImpl
import com.delavar.digipay.data.source.local.PrefSource
import com.delavar.digipay.data.source.local.PreferenceHelper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.delavar.digipay.data.source.local.PreferenceHelper.get
import com.delavar.digipay.domain.DomainScheduler
import com.delavar.digipay.presentation.utils.provider.ResourceProvider
import com.delavar.digipay.presentation.utils.provider.ResourceProviderImpl
import com.google.gson.Gson
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppComponentImpl(val app: Application) : AppComponent {
    override val pref: SharedPreferences by lazy {
        PreferenceHelper.customPrefs(app.applicationContext, "pref")
    }

    override val retrofit: Retrofit by lazy {
        val client = OkHttpClient.Builder().addInterceptor {
            val token: String? = pref[PrefSource.KEY_TOKEN]
            var request = it.request()
            token?.let {
                request = request.newBuilder()
                    .addHeader("Authorization", "Bearer $it")
                    .build()
            }

            it.proceed(request)
        }.build()


        Retrofit.Builder().client(client).baseUrl("https://api.spotify.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    override val apiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val resourceProvider by lazy {
        ResourceProviderImpl(app)
    }

    override val gson by lazy {
        Gson()
    }

    override val cloudSource: CloudSource by lazy {
        CloudSourceImpl(apiService, ErrorUtils(resourceProvider, gson))
    }

    override val domainScheduler: DomainScheduler by lazy {
        object : DomainScheduler {
            override fun io(): Scheduler = Schedulers.io()
            override fun ui(): Scheduler = AndroidSchedulers.mainThread()
        }
    }


}