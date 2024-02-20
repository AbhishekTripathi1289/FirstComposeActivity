package com.example.firstcomposeactivity.newsApp.di

import com.example.firstcomposeactivity.newsApp.api.NewsApiInterface
import com.example.firstcomposeactivity.newsApp.util.NewsConstant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NewsModule
{
    @Named("NewsRetrofit")
    @Provides
    @Singleton
    fun provideRetrofit():Retrofit
    {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }
    @Provides
    fun providesPostApi(@Named("NewsRetrofit") retrofit: Retrofit):NewsApiInterface
    {
        return retrofit.create(NewsApiInterface::class.java)
    }

}