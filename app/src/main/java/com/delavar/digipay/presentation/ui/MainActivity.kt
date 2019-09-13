package com.delavar.digipay.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.delavar.digipay.R
import android.net.Uri
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationResponse
import android.content.Intent
import android.widget.Toast
import com.spotify.sdk.android.authentication.AuthenticationRequest




class MainActivity : AppCompatActivity() {

    companion object{
        private const val AUTH_TOKEN_REQUEST_CODE=100
        private const val CLIENT_ID="ba05b9cd59634cefa8493ac961d76ed6"
        private const val REDIRECT_URI="http://mydigipay.com/"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.delavar.digipay.R.layout.activity_main)
        requestToken()
    }

    fun requestToken() {
        val request = getAuthenticationRequest(AuthenticationResponse.Type.TOKEN)
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
        val response = AuthenticationClient.getResponse(resultCode, data)

        if (AUTH_TOKEN_REQUEST_CODE == requestCode) {
            Toast.makeText(applicationContext,""+response.accessToken,Toast.LENGTH_LONG).show()
        } else {

        }
    }
}
