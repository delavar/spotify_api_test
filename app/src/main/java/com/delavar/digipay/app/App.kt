package com.delavar.digipay.app

import android.app.Application
import com.delavar.digipay.presentation.di.AppComponentImpl

class App : Application() {

    val appComponent = AppComponentImpl(this)

    override fun onCreate() {
        super.onCreate()
    }

}