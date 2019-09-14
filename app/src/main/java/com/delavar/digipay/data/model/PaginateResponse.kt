package com.delavar.digipay.data.model

import com.google.gson.annotations.SerializedName

data class PaginateResponse<T> (

	@SerializedName("href") val href : String,
	@SerializedName("items") val items : List<T>,
	@SerializedName("limit") val limit : Int,
	@SerializedName("next") val next : String,
	@SerializedName("offset") val offset : Int,
	@SerializedName("previous") val previous : String,
	@SerializedName("total") val total : Int
)