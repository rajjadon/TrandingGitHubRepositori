package com.raj.jadon.domain.repo

import com.raj.jadon.domain.dataState.DataState
import com.raj.jadon.domain.model.ClosedPullRequestUser
import kotlinx.coroutines.flow.Flow

interface IClosedPullRequestRepo {
    suspend fun getClosedPullRequest(): Flow<DataState<List<ClosedPullRequestUser>>>
}