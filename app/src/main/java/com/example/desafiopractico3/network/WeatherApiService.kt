package com.example.desafiopractico3.network

import com.example.desafiopractico3.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("current.json")
    suspend fun getWeatherByCapital(
        @Query("key") apiKey: String,
        @Query("q") capital: String
    ): WeatherResponse
}