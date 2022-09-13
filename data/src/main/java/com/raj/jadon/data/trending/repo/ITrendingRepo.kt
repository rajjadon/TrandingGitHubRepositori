package com.raj.jadon.data.trending.repo

import com.raj.jadon.domain.trandingRepositori.useCase.model.TrendingRepoDao
import com.raj.jadon.domain.dataState.DataState
import kotlinx.coroutines.flow.Flow

interface ITrendingRepo {

    suspend fun getTrendingRepo(
        language: String,
        since: String,
        spokenLanguageCode: String
    ): Flow<DataState<List<TrendingRepoDao>>>
}