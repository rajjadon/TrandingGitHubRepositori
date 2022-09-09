package com.raj.jadon.data.di

import com.raj.jadon.data.trending.repo.ITrendingRepo
import com.raj.jadon.data.trending.repo.TrendingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryProvider {

    @Binds
    abstract fun providesTrendingRepository(trendingRepositoryImpl: TrendingRepositoryImpl): ITrendingRepo
}