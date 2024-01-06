package com.example.countryapp.data

import com.example.countryapp.ui.model.Country

interface CountryRepository {
    suspend fun getCountries(name: String): List<Country>
    suspend fun getCountry(name: String): Country?
}