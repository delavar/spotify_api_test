package com.delavar.digipay.presentation.ui.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.delavar.digipay.R
import com.delavar.digipay.data.source.local.PrefSource
import com.delavar.digipay.presentation.utils.getAppComponent
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.delavar.digipay.data.source.local.PreferenceHelper.set
import com.delavar.digipay.databinding.ActivityAuthBinding
import com.delavar.digipay.presentation.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.spotify.sdk.android.authentication.AuthenticationResponse.Type.*
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {

    val pref by lazy { applicationContext.getAppComponent().pref }

    companion object {
        private const val AUTH_TOKEN_REQUEST_CODE = 100
        private const val CLIENT_ID = "ba05b9cd59634cefa8493ac961d76ed6"
        private const val REDIRECT_URI = "http://mydigipay.com/"

        fun start(context: Context) {
            val intent = Intent(context, AuthActivity::class.java)
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }

    fun onRequestTokenClick(v: View) {
        val request = getAuthenticationRequest(TOKEN)
        AuthenticationClient.openLoginActivity(this, AUTH_TOKEN_REQUEST_CODE, request)
    }

    private fun getAuthenticationRequest(type: AuthenticationResponse.Type): AuthenticationRequest {
        return AuthenticationRequest.Builder(CLIENT_ID, type, REDIRECT_URI)
            .setShowDialog(false)
            .setScopes(arrayOf("user-read-private"))
            .setCampaign("campaign-test")
            .build()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (AUTH_TOKEN_REQUEST_CODE == requestCode) {
            val response = AuthenticationClient.getResponse(resultCode, data)
            when(response.type){
                TOKEN -> {
                    pref[PrefSource.KEY_TOKEN] = response.accessToken
                    MainActivity.start(this)
                    finish()
                }
                ERROR -> {
                    Snackbar.make(container,response.error,Snackbar.LENGTH_LONG).show()
                }
                EMPTY -> {
                    Snackbar.make(container,getString(R.string.login_error),Snackbar.LENGTH_LONG).show()
                }
                else ->{

                }
            }
        }
    }
}
