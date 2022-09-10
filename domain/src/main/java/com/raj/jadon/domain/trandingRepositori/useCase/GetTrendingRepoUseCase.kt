package com.raj.jadon.domain.trandingRepositori.useCase

import com.raj.jadon.data.trending.repo.ITrendingRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTrendingRepoUseCase @Inject constructor(private val trendingRepo: ITrendingRepo)