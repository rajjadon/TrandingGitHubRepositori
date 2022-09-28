package com.raj.jadon.prasentation.trendingReposiotry

import androidx.fragment.app.activityViewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.raj.jadon.domain.dataState.DataState
import com.raj.jadon.prasentation.R
import com.raj.jadon.prasentation.common.base.BaseFragment
import com.raj.jadon.prasentation.common.extension.collectSharedFlowData
import com.raj.jadon.prasentation.common.extension.invisible
import com.raj.jadon.prasentation.common.extension.visible
import com.raj.jadon.prasentation.databinding.FragmentTrendingRepositoryBinding
import com.raj.jadon.prasentation.trendingReposiotry.adapter.ClosedPullRequestAdapter
import com.raj.jadon.prasentation.trendingReposiotry.adapter.TreadingRepositoryAdapter
import com.raj.jadon.prasentation.trendingReposiotry.viewModel.TreadingRepositoryViewModel

class TreadingRepositoryFragment :
    BaseFragment<FragmentTrendingRepositoryBinding>(R.layout.fragment_trending_repository),
    SwipeRefreshLayout.OnRefreshListener {

    private val trendingViewModel by activityViewModels<TreadingRepositoryViewModel>()
    private val adapter: TreadingRepositoryAdapter by lazy { TreadingRepositoryAdapter() }
    private val closedPullRequestAdapter: ClosedPullRequestAdapter by lazy { ClosedPullRequestAdapter() }

    override fun setDataCollector() {
        collectSharedFlowData(trendingViewModel.trendingRepo) {
            when (it) {
                is DataState.Error -> {
                    fragmentBinding.swipeRefresh.isRefreshing = false
                    fragmentBinding.shimmerViewContainer.stopShimmerAnimation()
                    fragmentBinding.shimmerViewContainer.invisible()
                    fragmentBinding.recyclerView.visible()
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
                    fragmentBinding.recyclerView.adapter = adapter
                }
            }
        }

        collectSharedFlowData(trendingViewModel.closedPullRequest) {
            when (it) {
                is DataState.Error -> {
                    fragmentBinding.swipeRefresh.isRefreshing = false
                    fragmentBinding.shimmerViewContainer.stopShimmerAnimation()
                    fragmentBinding.shimmerViewContainer.invisible()
                    fragmentBinding.recyclerView.visible()
                }
                is DataState.Loading -> {
                    fragmentBinding.shimmerViewContainer.startShimmerAnimation()
                    fragmentBinding.shimmerViewContainer.visible()
                }
                is DataState.Success -> {
                    fragmentBinding.swipeRefresh.isRefreshing = false
                    fragmentBinding.shimmerViewContainer.stopShimmerAnimation()
                    fragmentBinding.shimmerViewContainer.invisible()
                    closedPullRequestAdapter.submitList(it.baseResponseData)
                    fragmentBinding.recyclerView.adapter = closedPullRequestAdapter
                }
            }
        }
    }

    override fun setUpBindingVariables() {}

    override fun onStart() {
        super.onStart()

        fragmentBinding.shimmerViewContainer.startShimmerAnimation()
        fragmentBinding.shimmerViewContainer.visible()

//        trendingViewModel.getTrendingRepo(
//            language = "",
//            since = "daily",
//            spokenLanguageCode = ""
//        )

        trendingViewModel.getClosedPullRequest()
    }

    override fun setClickListener() {

        fragmentBinding.swipeRefresh.setOnRefreshListener(this)

        fragmentBinding.retry.setOnClickListener {
            onRefresh()
        }
    }

    override fun onRefresh() {
        fragmentBinding.swipeRefresh.isRefreshing = true
        trendingViewModel.getTrendingRepo(
            language = "",
            since = "daily",
            spokenLanguageCode = ""
        )
    }
}