package com.delavar.digipay.data.model

import com.google.gson.annotations.SerializedName

data class Error (

	@SerializedName("status") val status : Int,
	@SerializedName("message") val message : String
)