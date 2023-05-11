package com.example.myapplication.data.api

import com.example.myapplication.data.MainData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "https://newsapi.org/v2/"

interface ApiService {
    @GET("everything")
    fun getNews(
        @Query("q") query: String = "apple",
        @Query("from") from: String = "2023-05-10",
        @Query("to") to: String = "2023-05-10",
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = "b8d6ccbce0d14add9988ca623e775db3"
    ): Call<MainData>

    companion object {
        fun retrofitBuild(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}

