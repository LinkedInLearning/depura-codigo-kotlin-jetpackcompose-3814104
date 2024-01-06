package com.example.countryapp.data

import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountryApi {

    //https://restcountries.com/v3.1/name/{name}
    @GET("name/{name}")
    suspend fun getCountries(
        @Path("name") name: String
    ) : Response<List<CountryItem>>

    //https://restcountries.com/v3.1/name/{name}?fullText=true
    @GET("name/{name}?fullText=true")
    suspend fun getCountry(
        @Path("name") name: String
    ) : Response<List<CountryItem>>


    companion object {
        private const val BASE_URL = "https://restcountries.com/v3.1/"

        fun create(): CountryApi {
            val gson = GsonBuilder()
                .create()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(CountryApi::class.java)
        }
    }
}