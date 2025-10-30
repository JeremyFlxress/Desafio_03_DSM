package com.example.desafiopractico3.controller

import com.example.desafiopractico3.network.RestCountriesClient

class MainController {

    suspend fun getRegions(): List<String> {
        val countries = RestCountriesClient.apiService.getAllCountries()
        return countries
            .map { it.region }
            .distinct()
            .sorted()
    }
}
