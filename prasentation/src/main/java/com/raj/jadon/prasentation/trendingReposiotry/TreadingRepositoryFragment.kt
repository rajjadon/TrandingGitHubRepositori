package com.raj.jadon.prasentation.trendingReposiotry

import com.raj.jadon.prasentation.R
import com.raj.jadon.prasentation.common.base.BaseFragment
import com.raj.jadon.prasentation.databinding.FragmentTrendingRepositoryBinding
import com.raj.jadon.prasentation.trendingReposiotry.viewModel.TreadingRepositoryViewModel
import javax.inject.Inject

class TreadingRepositoryFragment :
    BaseFragment<FragmentTrendingRepositoryBinding>(R.layout.fragment_trending_repository) {

    @Inject
    lateinit var trendingViewModel: TreadingRepositoryViewModel

    override fun setDataCollector() {}

    override fun setUpBindingVariables() {}

    override fun setClickListener() {}
}