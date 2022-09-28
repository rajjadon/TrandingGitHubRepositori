package com.raj.jadon.data.trending.repo.tendingRepo

import com.raj.jadon.data.trending.repo.tendingRepo.mapper.TrendingMapper
import com.raj.jadon.domain.model.TrendingRepoDao
import com.raj.jadon.domain.dataState.DataState
import com.raj.jadon.domain.repo.ITrendingRepo
import com.raj.jadon.network.networkService.NetworkService
import com.raj.jadon.data.safeApiRequest.SafeApiRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TrendingRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val safeApiRequest: SafeApiRequest,
    private val trendingMapper: TrendingMapper
) : ITrendingRepo {
    override suspend fun getTrendingRepo(
        language: String,
        since: String,
        spokenLanguageCode: String
    ): Flow<DataState<List<TrendingRepoDao>>> = flow {

        val response = safeApiRequest.apiRequest {
            networkService.getTrendingRepo(
                language = language,
                since = since,
                spokenLanguageCode = spokenLanguageCode
            )
        }

        when (response) {
            is DataState.Success -> emit(DataState.Success(trendingMapper.mapToDao(response.baseResponseData)))
            is DataState.Error -> emit(response)
            is DataState.Loading -> emit(response)
        }
    }
}
