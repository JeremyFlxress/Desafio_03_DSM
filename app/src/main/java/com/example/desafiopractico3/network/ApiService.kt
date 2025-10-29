package com.example.desafiopractico3.network

import retrofit2.http.GET
import retrofit2.http.Path
import com.example.desafiopractico3.model.CountryResponse

interface ApiService {

    // --- PARA TU PARTE (Parte 1) ---
    // Endpoint para "GET /v3.1/all" [cite: 1789]
    // Usamos 'suspend' para que funcione con Corutinas [cite: 2410]
    @GET("v3.1/all")
    suspend fun getAllCountries(): List<CountryResponse>


    // --- PARA LA PARTE 2 ---
    // Endpoint para "GET /v3.1/region/{region}" [cite: 1790]
    // @Path reemplaza "{regionName}" con el valor de la variable [cite: 1374]
    @GET("v3.1/region/{regionName}")
    suspend fun getCountriesByRegion(
        @Path("regionName") region: String
    ): List<CountryResponse>

    // NOTA: La API de Clima (Parte 3) usa una URL base diferente.
    // La definiremos por separado cuando lleguemos a esa parte.
}