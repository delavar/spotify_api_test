package com.delavar.digipay.presentation.di

import android.app.Application
import android.content.SharedPreferences
import com.delavar.digipay.data.restful.ApiService
import com.delavar.digipay.data.source.local.PreferenceHelper
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class AppComponentImpl(val app: Application) : AppComponent {

    override val pref: SharedPreferences by lazy {
        PreferenceHelper.customPrefs(app.applicationContext,"pref")
    }

    override val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl("https://api.spotify.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    override val apiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}