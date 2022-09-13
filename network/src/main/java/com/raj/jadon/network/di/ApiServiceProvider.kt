package com.raj.jadon.network.di

import com.raj.jadon.network.networkService.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceProvider {

    @Provides
    @Singleton
    fun providesNetworkService(retrofit: Retrofit.Builder): NetworkService =
        retrofit.build().create(NetworkService::class.java)
}