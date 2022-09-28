package com.raj.jadon.domain.trandingRepositori.useCase

import com.raj.jadon.domain.dataState.DataState
import com.raj.jadon.domain.repo.ITrendingRepo
import com.raj.jadon.domain.model.TrendingRepoDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTrendingRepoUseCase @Inject constructor(private val trendingRepo: ITrendingRepo) {
    suspend fun getTrendingRepo(
        language: String,
        since: String,
        spokenLanguageCode: String
    ): Flow<DataState<List<TrendingRepoDao>>> {
        return trendingRepo.getTrendingRepo(
            language = language,
            since = since,
            spokenLanguageCode = spokenLanguageCode
        )
    }
}