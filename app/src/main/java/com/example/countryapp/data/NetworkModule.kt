package com.example.countryapp.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideCountryApi(): CountryApi {
        return CountryApi.create()
    }


    @Singleton
    @Provides
    fun provideCountryRepository(api: CountryApi): CountryRepository {
        return CountryRepositoryImpl.getInstance(api)
    }
}