package com.example.firstcomposeactivity.firstMVVMImplementation.di

import com.example.firstcomposeactivity.firstMVVMImplementation.network.PostApi
import com.example.firstcomposeactivity.firstMVVMImplementation.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PostModule
{
    @Provides
    @Singleton
    fun provideRetrofit():Retrofit
    {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }
    @Provides
    fun providesPostApi(retrofit: Retrofit):PostApi
    {
        return retrofit.create(PostApi::class.java)
    }

}