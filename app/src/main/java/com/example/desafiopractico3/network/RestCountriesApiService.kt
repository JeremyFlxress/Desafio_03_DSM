package com.example.desafiopractico3.network

import retrofit2.http.GET
import retrofit2.http.Path
import com.example.desafiopractico3.model.CountryResponse
import retrofit2.http.Query

interface RestCountriesApiService {

    @GET("v3.1/all")
    suspend fun getAllCountries(@Query("fields") fields: String = "name,capital,region,subregion,population,flags,currencies,languages,latlng"): List<CountryResponse>

    // @Path reemplaza "{regionName}" con el valor de la variable
    @GET("v3.1/region/{regionName}")
    suspend fun getCountriesByRegion(
        @Path("regionName") region: String,
        @Query("fields") fields: String = "name,capital,flags,latlng,subregion,population,currencies,languages,region"
    ): List<CountryResponse>

}