package com.raj.jadon.network.networkService

import com.raj.jadon.network.ALL_CLOSED_PULL_REQUEST_API
import com.raj.jadon.network.model.PullRequestUser
import retrofit2.http.GET

interface PullRequestService {
    @GET(ALL_CLOSED_PULL_REQUEST_API)
    suspend fun getClosedPullRequest(): List<PullRequestUser>
}