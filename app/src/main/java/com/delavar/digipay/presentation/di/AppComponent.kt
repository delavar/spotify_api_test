package com.delavar.digipay.presentation.di

import android.content.SharedPreferences
import com.delavar.digipay.data.restful.ApiService
import com.delavar.digipay.data.source.cloud.CloudSource
import com.delavar.digipay.domain.DomainScheduler
import com.delavar.digipay.presentation.utils.provider.ResourceProvider
import com.google.gson.Gson
import retrofit2.Retrofit

interface AppComponent {
    val retrofit: Retrofit
    val apiService : ApiService
    val pref : SharedPreferences
    val resourceProvider : ResourceProvider
    val gson : Gson
    val cloudSource : CloudSource
    val domainScheduler : DomainScheduler
}