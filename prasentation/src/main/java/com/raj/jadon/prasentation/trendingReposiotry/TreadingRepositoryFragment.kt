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
                is DataState.Error -> {
                    fragmentBinding.swipeRefresh.isRefreshing = false
                    Timber.e(it.errorMessage)
                }
                is DataState.Loading -> fragmentBinding.swipeRefresh.isRefreshing = true
                is DataState.Success -> {
                    fragmentBinding.swipeRefresh.isRefreshing = false
                    adapter.submitList(it.baseResponseData)
                }
            }
        }
    }

    override fun setUpBindingVariables() {
        fragmentBinding.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        trendingViewModel.getTrendingRepo(
            language = "",
            since = "daily",
            spokenLanguageCode = ""
        )
    }

    override fun setClickListener() {

        fragmentBinding.swipeRefresh.setOnRefreshListener {
            trendingViewModel.getTrendingRepo(
                language = "",
                since = "daily",
                spokenLanguageCode = ""
            )
        }
    }
}