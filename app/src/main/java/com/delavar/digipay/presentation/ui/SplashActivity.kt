package com.delavar.digipay.presentation.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.delavar.digipay.R
import com.delavar.digipay.data.source.local.PrefSource
import com.delavar.digipay.data.source.local.PreferenceHelper.get
import com.delavar.digipay.presentation.ui.auth.AuthActivity
import com.delavar.digipay.presentation.utils.getAppComponent

class SplashActivity : AppCompatActivity() {
    val pref by lazy { applicationContext.getAppComponent().pref }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({ checkToken() }, 2000)
    }

    fun checkToken() {
        val token: String = pref[PrefSource.KEY_TOKEN] ?: ""
        if (token.isEmpty()) {
            AuthActivity.start(this)
        } else {
            MainActivity.start(this)
        }
        finish()
    }
}
