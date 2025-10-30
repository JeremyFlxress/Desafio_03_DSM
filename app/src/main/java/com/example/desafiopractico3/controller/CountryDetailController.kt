package com.example.desafiopractico3.controller

import com.example.desafiopractico3.model.WeatherResponse
import com.example.desafiopractico3.network.WeatherApiClient

class CountryDetailController {

    suspend fun getWeatherByCapital(apiKey: String, capital: String): WeatherResponse {
        return WeatherApiClient.apiService.getWeatherByCapital(apiKey, capital)
    }
}
