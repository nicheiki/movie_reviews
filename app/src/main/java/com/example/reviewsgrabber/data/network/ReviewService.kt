package com.example.reviewsgrabber.data.network

import com.example.reviewsgrabber.data.models.Container
import retrofit2.http.GET
import retrofit2.http.Query

interface ReviewService {

    @GET("reviews/all.json")
    suspend fun getApiModels(
        @Query("api-key") key: String,
        @Query("offset") offset: Int,
    ): Container

}