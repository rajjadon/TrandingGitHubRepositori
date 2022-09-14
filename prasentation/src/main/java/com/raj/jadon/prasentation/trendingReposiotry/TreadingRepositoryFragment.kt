package com.raj.jadon.prasentation.trendingReposiotry

import androidx.fragment.app.activityViewModels
import com.raj.jadon.domain.dataState.DataState
import com.raj.jadon.prasentation.R
import com.raj.jadon.prasentation.common.base.BaseFragment
import com.raj.jadon.prasentation.common.extension.collectSharedFlowData
import com.raj.jadon.prasentation.common.extension.invisible
import com.raj.jadon.prasentation.common.extension.visible
import com.raj.jadon.prasentation.databinding.FragmentTrendingRepositoryBinding
import com.raj.jadon.prasentation.trendingReposiotry.adapter.TreadingRepositoryAdapter
import com.raj.jadon.prasentation.trendingReposiotry.viewModel.TreadingRepositoryViewModel
import timber.log.Timber

class TreadingRepositoryFragment :
    BaseFragment<FragmentTrendingRepositoryBinding>(R.layout.fragment_trending_repository) {

    private val trendingViewModel by activityViewModels<TreadingRepositoryViewModel>()
    private val adapter: TreadingRepositoryAdapter by lazy { TreadingRepositoryAdapter() }

    override fun setDataCollector() {
        collectSharedFlowData(trendingViewModel.trendingRepo) {
            when (it) {
                is DataState.Error -> {
                    fragmentBinding.swipeRefresh.isRefreshing = false
                    fragmentBinding.shimmerViewContainer.stopShimmerAnimation()
                    fragmentBinding.shimmerViewContainer.invisible()
                    Timber.e(it.errorMessage)
                }
                is DataState.Loading -> {
                    fragmentBinding.shimmerViewContainer.startShimmerAnimation()
                    fragmentBinding.shimmerViewContainer.visible()
                }
                is DataState.Success -> {
                    fragmentBinding.swipeRefresh.isRefreshing = false
                    fragmentBinding.shimmerViewContainer.stopShimmerAnimation()
                    fragmentBinding.shimmerViewContainer.invisible()
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

        fragmentBinding.shimmerViewContainer.startShimmerAnimation()
        fragmentBinding.shimmerViewContainer.visible()

        trendingViewModel.getTrendingRepo(
            language = "",
            since = "daily",
            spokenLanguageCode = ""
        )
    }

    override fun setClickListener() {

        fragmentBinding.swipeRefresh.setOnRefreshListener {
            fragmentBinding.swipeRefresh.isRefreshing = true
            trendingViewModel.getTrendingRepo(
                language = "",
                since = "daily",
                spokenLanguageCode = ""
            )
        }
    }
}