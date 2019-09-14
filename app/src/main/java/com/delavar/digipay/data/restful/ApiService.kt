package com.delavar.digipay.data.restful

import SearchResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    fun search(
        @Query("q") query: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("type") type: String = "artist"
    ): Flowable<SearchResponse>
}