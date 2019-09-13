package com.delavar.digipay.presentation.di

import android.content.SharedPreferences
import com.delavar.digipay.data.restful.ApiService
import retrofit2.Retrofit

interface AppComponent {
    val retrofit: Retrofit
    val apiService : ApiService
    val pref : SharedPreferences
}