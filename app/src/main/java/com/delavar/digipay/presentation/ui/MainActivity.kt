package com.delavar.digipay.presentation.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.delavar.digipay.R
import android.net.Uri
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationResponse
import android.content.Intent
import android.widget.Toast
import com.delavar.digipay.presentation.ui.search.SearchFragment
import com.spotify.sdk.android.authentication.AuthenticationRequest

class MainActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, SearchFragment.newInstance())
                .commit()
    }
}
