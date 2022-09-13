package com.raj.jadon.prasentation.trendingReposiotry

import androidx.fragment.app.activityViewModels
import com.raj.jadon.domain.dataState.DataState
import com.raj.jadon.prasentation.R
import com.raj.jadon.prasentation.common.base.BaseFragment
import com.raj.jadon.prasentation.common.extension.collectSharedFlowData
import com.raj.jadon.prasentation.databinding.FragmentTrendingRepositoryBinding
import com.raj.jadon.prasentation.trendingReposiotry.adapter.TreadingRepositoryAdapter
import com.raj.jadon.prasentation.trendingReposiotry.viewModel.TreadingRepositoryViewModel
import timber.log.Timber
import java.util.*

class TreadingRepositoryFragment :
    BaseFragment<FragmentTrendingRepositoryBinding>(R.layout.fragment_trending_repository) {

    private val trendingViewModel by activityViewModels<TreadingRepositoryViewModel>()
    private val adapter: TreadingRepositoryAdapter by lazy { TreadingRepositoryAdapter() }

    override fun setDataCollector() {
        collectSharedFlowData(trendingViewModel.trendingRepo) {
            when (it) {
                is DataState.Error -> Timber.e(it.errorMessage)
                DataState.Loading -> Timber.e(it.toString())
                is DataState.Success -> adapter.submitList(it.baseResponseData)
            }
        }

        trendingViewModel.getTrendingRepo(
            language = "kotlin",
            since = "daily",
            spokenLanguageCode = Locale.getDefault().language
        )
    }

    override fun setUpBindingVariables() {
        fragmentBinding.adapter = adapter
    }

    override fun setClickListener() {}
}