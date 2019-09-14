package com.delavar.digipay.data.restful

import com.delavar.digipay.data.model.SearchResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    fun search(
        @Query("query") query: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("type") type: String = "artist"
    ): Flowable<SearchResponse>
}