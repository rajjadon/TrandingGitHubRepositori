package com.raj.jadon.prasentation.trendingReposiotry.adapter

import android.graphics.Color
import androidx.recyclerview.widget.DiffUtil
import com.example.application.common.BaseAdapter
import com.raj.jadon.domain.model.TrendingRepoDao
import com.raj.jadon.prasentation.R
import com.raj.jadon.prasentation.common.extension.loadImage
import com.raj.jadon.prasentation.databinding.TrendingRvItemBinding

class TreadingRepositoryAdapter :
    BaseAdapter<TrendingRepoDao, TrendingRvItemBinding>(object :
        DiffUtil.ItemCallback<TrendingRepoDao>() {
        override fun areItemsTheSame(oldItem: TrendingRepoDao, newItem: TrendingRepoDao): Boolean {
            return oldItem.avatar == newItem.avatar
        }

        override fun areContentsTheSame(
            oldItem: TrendingRepoDao,
            newItem: TrendingRepoDao
        ): Boolean {
            return oldItem == newItem
        }

    }, R.layout.trending_rv_item) {
    override fun bind(viewBinding: TrendingRvItemBinding, item: TrendingRepoDao, position: Int) {

        viewBinding.repoImage.loadImage(item.avatar)
        viewBinding.repoName.text = item.name
        viewBinding.repoDes.text = item.description

        viewBinding.language.text = item.language
        viewBinding.languageColor.setColorFilter(Color.parseColor(item.languageColor))
        viewBinding.startText.text = item.currentPeriodStars.toString()
        viewBinding.forkText.text = item.forks.toString()

        var isCLicked = false

        viewBinding.root.setOnClickListener {
            if (isCLicked) {
                viewBinding.isCLicked = false
                isCLicked = false
            } else {
                viewBinding.isCLicked = true
                isCLicked = true
            }
        }
    }
}