package com.example.countryapp.data

import com.example.countryapp.toCountry
import com.example.countryapp.ui.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Singleton
import javax.inject.Inject


@Singleton
class CountryRepositoryImpl @Inject constructor(private val countryApi: CountryApi) : CountryRepository {


    companion object {
        @Volatile private var instance: CountryRepository? = null

        fun getInstance(countryApi: CountryApi) =
            instance ?: synchronized(this) {
                instance ?: CountryRepositoryImpl(countryApi).also { instance = it }
            }
    }

    override suspend fun getCountries(name: String): List<Country> {
        return withContext(Dispatchers.IO) {
            val response = countryApi.getCountries(name)
            if (response.isSuccessful) {
                response.body()?.map { it.toCountry() } ?: emptyList<Country>()
            } else {
                emptyList<Country>()
            }
        }
    }

    override suspend fun getCountry(name: String): Country? {
        return withContext(Dispatchers.IO) {
            val response = countryApi.getCountry(name)
            if (response.isSuccessful) {
                response.body()?.map { it.toCountry() }?.first()
            } else {
                null
            }
        }
    }


}