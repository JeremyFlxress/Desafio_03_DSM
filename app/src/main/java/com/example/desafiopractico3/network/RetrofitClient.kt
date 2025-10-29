package com.example.desafiopractico3.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Usamos 'object' para crear un Singleton
object RetrofitClient {

    // URL Base de la API de RestCountries
    private const val BASE_URL = "https://restcountries.com/"

    // Creamos un interceptor para ver los logs de la API (muy útil para depurar)
    // Como en la Guía 12
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    // Creamos la instancia de Retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create()) // Usamos Gson
        .build()

    // Creamos una 'instancia pública' de nuestro ApiService
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}