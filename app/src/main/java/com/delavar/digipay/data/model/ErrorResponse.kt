package com.delavar.digipay.data.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse (
	@SerializedName("error") val error : Error
)