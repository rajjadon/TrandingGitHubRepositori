package com.raj.jadon.domain.trandingRepositori.di

import com.raj.jadon.data.trending.repo.ITrendingRepo
import com.raj.jadon.domain.trandingRepositori.useCase.GetTrendingRepoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideUseCase {

    @Provides
    @Singleton
    fun provideGetTrendingRepoUseCase(trendingRepo: ITrendingRepo) =
        GetTrendingRepoUseCase(trendingRepo)
}