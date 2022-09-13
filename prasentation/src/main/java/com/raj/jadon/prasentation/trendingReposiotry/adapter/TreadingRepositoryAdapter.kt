package com.raj.jadon.prasentation.trendingReposiotry.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.application.common.BaseAdapter
import com.raj.jadon.domain.trandingRepositori.useCase.model.TrendingRepoDao
import com.raj.jadon.prasentation.R
import com.raj.jadon.prasentation.common.extension.loadImage
import com.raj.jadon.prasentation.databinding.TrendingRvItemBinding

class TreadingRepositoryAdapter :
    BaseAdapter<TrendingRepoDao, TrendingRvItemBinding>(object :
        DiffUtil.ItemCallback<TrendingRepoDao>() {
        override fun areItemsTheSame(oldItem: TrendingRepoDao, newItem: TrendingRepoDao): Boolean {
            return oldItem.forks == newItem.forks
        }

        override fun areContentsTheSame(
            oldItem: TrendingRepoDao,
            newItem: TrendingRepoDao
        ): Boolean {
            return oldItem.forks == newItem.forks
        }

    }, R.layout.trending_rv_item) {
    override fun bind(viewBinding: TrendingRvItemBinding, item: TrendingRepoDao, position: Int) {
        viewBinding.repoImage.loadImage(item.avatar, 10)
        viewBinding.repoName.text = item.name
    }
}