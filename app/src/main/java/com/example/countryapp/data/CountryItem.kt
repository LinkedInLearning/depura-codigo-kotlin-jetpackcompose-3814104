package com.example.countryapp.data

import com.google.gson.annotations.SerializedName

data class CountryItem(
    val altSpellings: List<String>, //Alternate spellings of the country name
    val area: Double, //Geographical size km2
    val borders: List<String>, //Border countries ()
    val capital: List<String>?, //Capital cities
    val capitalInfo: CapitalInfo, //Capital latitude and longitude
    val cca2: String, //ISO 3166-1 alpha-2 two-letter country codes
    val cca3: String, //ISO 3166-1 alpha-3 three-letter country codes
    val continents: List<String>,
    @SerializedName("currencies")
    val currencies: Map<String, CurrencyInfo>?,
    val flag: String, // flag emoji
    val flags: Flags, // Flagpedia links to svg and png flags
    val independent: Boolean, //ISO 3166-1 independence status (the country is considered a sovereign state)
    val landlocked: Boolean,
    @SerializedName("languages")
    val languages:  Map<String, String>?,
    val latlng: List<Double>,
    val name: Name,
    val population: Int,
    val region: String,
    val startOfWeek: String,
    val status: String,
    val subregion: String,
    val timezones: List<String>,
    val maps: Maps
)

data class Maps(
    val googleMaps: String,
    val openStreetMaps: String
)

data class CapitalInfo(
    val latlng: List<Double>
)
data class CurrencyInfo(
    val name: String,
    val symbol: String
)
data class Flags(
    val alt: String,
    val png: String,
    val svg: String
)
data class LanguagesInfo (
    val name: String
)
data class Name(
    val common: String,
    @SerializedName("nativeName")
    val nativeName: Map<String, NativeName>,
    val official: String
)

data class NativeName(
    val common: String,
    val official: String
)