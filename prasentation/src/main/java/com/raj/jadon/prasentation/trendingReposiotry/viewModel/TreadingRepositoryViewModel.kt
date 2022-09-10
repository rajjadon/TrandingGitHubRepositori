package com.raj.jadon.prasentation.trendingReposiotry.viewModel

import androidx.lifecycle.ViewModel
import com.raj.jadon.domain.trandingRepositori.useCase.GetTrendingRepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TreadingRepositoryViewModel @Inject constructor(
    private val getTrendingRepoUseCase: GetTrendingRepoUseCase
) : ViewModel() {

    fun getFun() {
        Timber.e("Hi............")
    }
}