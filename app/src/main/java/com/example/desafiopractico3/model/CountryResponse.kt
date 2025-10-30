package com.example.desafiopractico3.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

// Esta es la clase principal para cada país en la lista
@Parcelize
data class CountryResponse(
    @SerializedName("name")
    val name: Name,

    val capital: List<String>?,

    val region: String,

    val subregion: String?,

    val population: Long,

    @SerializedName("flags")
    val flags: Flags,

    val currencies: HashMap<String, Currency>?,

    val languages: HashMap<String, String>?,

    val latlng: List<Double>?
) : Parcelable

// Clase para el objeto anidado "name"
@Parcelize
data class Name(
    val common: String,
    val official: String
) : Parcelable

// Clase para el objeto anidado "flags"
@Parcelize
data class Flags(
    val png: String,
    val svg: String
) : Parcelable

// Clase para el objeto anidado "currencies"
@Parcelize
data class Currency(
    val name: String,
    val symbol: String?
) : Parcelable