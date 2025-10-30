package com.example.desafiopractico3.controller

import com.example.desafiopractico3.model.CountryResponse
import com.example.desafiopractico3.network.RestCountriesClient

class CountryListController {

    suspend fun getCountriesByRegion(region: String): List<CountryResponse> {
        return RestCountriesClient.apiService.getCountriesByRegion(region)
    }
}
