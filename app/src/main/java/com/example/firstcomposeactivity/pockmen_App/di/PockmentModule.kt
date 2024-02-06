package com.example.firstcomposeactivity.pockmen_App.di

import com.example.firstcomposeactivity.pockmen_App.api.PokemonApi
import com.example.firstcomposeactivity.pockmen_App.util.PokemonConstants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module()
object PockmentModule
{
    @Singleton
    @Provides
    fun providePokeApi(): PokemonApi {

        val logging = HttpLoggingInterceptor()

        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .build()
            .create(PokemonApi::class.java)
    }
}