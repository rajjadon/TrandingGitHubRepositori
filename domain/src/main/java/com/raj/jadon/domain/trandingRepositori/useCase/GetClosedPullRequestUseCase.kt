package com.raj.jadon.domain.trandingRepositori.useCase

import com.raj.jadon.domain.dataState.DataState
import com.raj.jadon.domain.model.ClosedPullRequestUser
import com.raj.jadon.domain.repo.IClosedPullRequestRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetClosedPullRequestUseCase @Inject constructor(private val iClosedPullRequestRepo: IClosedPullRequestRepo) {
    suspend fun getClosedPullRequest(): Flow<DataState<List<ClosedPullRequestUser>>> {
        return iClosedPullRequestRepo.getClosedPullRequest()
    }
}