package com.raj.jadon.prasentation.trendingReposiotry

import androidx.fragment.app.viewModels
import com.raj.jadon.prasentation.R
import com.raj.jadon.prasentation.common.base.BaseFragment
import com.raj.jadon.prasentation.databinding.FragmentTrendingRepositoryBinding
import com.raj.jadon.prasentation.trendingReposiotry.viewModel.TreadingRepositoryViewModel

class TreadingRepositoryFragment :
    BaseFragment<FragmentTrendingRepositoryBinding>(R.layout.fragment_trending_repository) {

    private val trendingViewModel by viewModels<TreadingRepositoryViewModel>()

    override fun setDataCollector() {}

    override fun setUpBindingVariables() {
        trendingViewModel.getFun()
    }

    override fun setClickListener() {}
}