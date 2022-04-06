package com.example.assignment13_mvvm_kotlin.module

import com.example.assignment13_mvvm_kotlin.network.RetroService
import com.example.assignment13_mvvm_kotlin.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    @Singleton
    fun getRetroInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getPageService(retrofit: Retrofit) : RetroService{
        return retrofit.create(RetroService::class.java)
    }

}