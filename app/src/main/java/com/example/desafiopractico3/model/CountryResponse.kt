package com.example.desafiopractico3.model

import com.google.gson.annotations.SerializedName

// Esta es la clase principal para cada país en la lista
data class CountryResponse(
    // Usamos @SerializedName para conectar la clave "name" del JSON
    // con nuestra variable "nameInfo" [cite: 1282]
    @SerializedName("name")
    val nameInfo: Name,

    val capital: List<String>?, // La API a veces no trae capital

    val region: String,

    val subregion: String?,

    val population: Long,

    @SerializedName("flags")
    val flagInfo: Flags,

    val currencies: Map<String, Currency>?, // Objeto de objetos

    val languages: Map<String, String>? // Objeto de strings
)

// Clase para el objeto anidado "name"
data class Name(
    val common: String,
    val official: String
)

// Clase para el objeto anidado "flags"
data class Flags(
    val png: String,
    val svg: String
)

// Clase para el objeto anidado "currencies"
data class Currency(
    val name: String,
    val symbol: String?
)