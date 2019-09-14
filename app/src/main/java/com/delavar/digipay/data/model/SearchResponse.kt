package com.delavar.digipay.data.model

import com.delavar.digipay.domain.model.Artist
import com.google.gson.annotations.SerializedName

data class SearchResponse (

	@SerializedName("artists") val artists : PaginateResponse<Artist>
)