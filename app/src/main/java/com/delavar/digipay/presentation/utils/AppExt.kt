package com.delavar.digipay.presentation.utils

import android.content.Context
import com.delavar.digipay.app.App
import com.delavar.digipay.presentation.di.AppComponent

fun Context.getAppComponent() : AppComponent {
    return (this as App).appComponent
}