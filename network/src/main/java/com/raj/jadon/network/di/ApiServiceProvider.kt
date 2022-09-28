package com.raj.jadon.network.di

import com.raj.jadon.network.networkService.NetworkService
import com.raj.jadon.network.networkService.PullRequestService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceProvider {

    @Provides
    @Singleton
    fun providesNetworkService(@Named("GITHUB_TRENDING_REPO") retrofit: Retrofit.Builder): NetworkService =
        retrofit.build().create(NetworkService::class.java)

    @Provides
    @Singleton
    fun providesPullRequestNetworkService(@Named("GITHUB_PULL_REQUEST") retrofit: Retrofit.Builder): PullRequestService =
        retrofit.build().create(PullRequestService::class.java)
}