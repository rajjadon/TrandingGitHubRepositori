package com.raj.jadon.trandinggithubrepositori.di

import com.raj.jadon.data.trending.repo.closedPullRequestRepo.mapper.ClosedPullRequestRepoImpl
import com.raj.jadon.data.trending.repo.tendingRepo.TrendingRepositoryImpl
import com.raj.jadon.domain.repo.IClosedPullRequestRepo
import com.raj.jadon.domain.repo.ITrendingRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryProvider {

    @Binds
    abstract fun providesTrendingRepository(trendingRepositoryImpl: TrendingRepositoryImpl): ITrendingRepo

    @Binds
    abstract fun providesClosedPullRequestRepository(closedPullRequestRepoImpl: ClosedPullRequestRepoImpl): IClosedPullRequestRepo
}