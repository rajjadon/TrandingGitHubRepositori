package com.raj.jadon.data.trending.repo.closedPullRequestRepo.mapper

import com.raj.jadon.data.safeApiRequest.SafeApiRequest
import com.raj.jadon.domain.dataState.DataState
import com.raj.jadon.domain.model.ClosedPullRequestUser
import com.raj.jadon.domain.repo.IClosedPullRequestRepo
import com.raj.jadon.network.networkService.NetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ClosedPullRequestRepoImpl @Inject constructor(
    private val networkService: NetworkService,
    private val safeApiRequest: SafeApiRequest,
    private val closedPullRequestMapper: ClosedPullRequestMapper
) : IClosedPullRequestRepo {
    override suspend fun getClosedPullRequest(): Flow<DataState<List<ClosedPullRequestUser>>> =
        flow {
            when (val response =
                safeApiRequest.apiRequest { networkService.getClosedPullRequest() }) {
                is DataState.Success -> emit(
                    DataState.Success(
                        closedPullRequestMapper.mapToDao(
                            response.baseResponseData
                        )
                    )
                )
                is DataState.Error -> emit(response)
                is DataState.Loading -> emit(response)
            }
        }
}