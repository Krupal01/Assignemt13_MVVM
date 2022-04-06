package com.example.assignment13_mvvm_kotlin.network

import com.example.assignment13_mvvm_kotlin.model.Page
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("/api/v1/search_by_date/")
    suspend fun getRemotePage(@Query("tags") tags: String?, @Query("page") page: Int): Page
}