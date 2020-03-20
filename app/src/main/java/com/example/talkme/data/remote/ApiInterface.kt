package com.example.talkme.data.remote

import com.example.talkme.data.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("search")
    fun getSearch(
        @Query("part") part: String?,
        @Query("order") order: String?,
        @Query("key") pageToken: String?,
        @Query("q") q: String?
    ): Call<SearchResponse>
}