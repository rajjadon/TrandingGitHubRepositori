package com.raj.jadon.data.trending.repo

import com.raj.jadon.network.safeApiRequest.SafeApiRequest
import javax.inject.Inject

class TrendingRepositoryImpl @Inject constructor(private val safeApiRequest: SafeApiRequest) :
    ITrendingRepo