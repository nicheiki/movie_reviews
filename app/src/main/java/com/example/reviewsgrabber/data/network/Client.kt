package com.example.reviewsgrabber.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.nytimes.com/svc/movies/v2/"

object Client {
    val apiClient: ReviewService by lazy {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        return@lazy retrofit.create(ReviewService::class.java)
    }
}
