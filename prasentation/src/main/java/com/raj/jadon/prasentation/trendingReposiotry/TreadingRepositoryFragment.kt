package com.raj.jadon.prasentation.trendingReposiotry

import androidx.fragment.app.activityViewModels
import com.raj.jadon.prasentation.R
import com.raj.jadon.prasentation.common.base.BaseFragment
import com.raj.jadon.prasentation.databinding.FragmentTrendingRepositoryBinding
import com.raj.jadon.prasentation.trendingReposiotry.adapter.TreadingRepositoryAdapter
import com.raj.jadon.prasentation.trendingReposiotry.viewModel.TreadingRepositoryViewModel

class TreadingRepositoryFragment :
    BaseFragment<FragmentTrendingRepositoryBinding>(R.layout.fragment_trending_repository) {

    private val trendingViewModel by activityViewModels<TreadingRepositoryViewModel>()
    private val adapter: TreadingRepositoryAdapter by lazy { TreadingRepositoryAdapter() }

    override fun setDataCollector() {}

    override fun setUpBindingVariables() {
        fragmentBinding.adapter = adapter
    }

    override fun setClickListener() {
        adapter.listener = { _, _, _ ->

        }
    }
}