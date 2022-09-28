package com.raj.jadon.prasentation.trendingReposiotry.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raj.jadon.domain.dataState.DataState
import com.raj.jadon.domain.model.ClosedPullRequestUser
import com.raj.jadon.domain.model.TrendingRepoDao
import com.raj.jadon.domain.trandingRepositori.useCase.GetClosedPullRequestUseCase
import com.raj.jadon.domain.trandingRepositori.useCase.GetTrendingRepoUseCase
import com.raj.jadon.prasentation.common.extension.toSharedFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TreadingRepositoryViewModel @Inject constructor(
    private val getTrendingRepoUseCase: GetTrendingRepoUseCase,
    private val getClosedPullRequestUseCase: GetClosedPullRequestUseCase
) : ViewModel() {

    private val _trendingRepo = MutableSharedFlow<DataState<List<TrendingRepoDao>>>()
    val trendingRepo = _trendingRepo.toSharedFlow()

    private val _closedPullRequest = MutableSharedFlow<DataState<List<ClosedPullRequestUser>>>()
    val closedPullRequest = _closedPullRequest.toSharedFlow()

    fun getTrendingRepo(
        language: String,
        since: String,
        spokenLanguageCode: String
    ) {
        viewModelScope.launch {
            getTrendingRepoUseCase.getTrendingRepo(
                language = language,
                since = since,
                spokenLanguageCode = spokenLanguageCode
            ).onEach {
                _trendingRepo.emit(it)
            }.launchIn(this)
        }
    }

    fun getClosedPullRequest() {
        viewModelScope.launch {
            getClosedPullRequestUseCase.getClosedPullRequest().onEach {
                _closedPullRequest.emit(it)
            }.launchIn(this)
        }
    }
}