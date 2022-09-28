package com.raj.jadon.prasentation.trendingReposiotry.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.application.common.BaseAdapter
import com.raj.jadon.domain.model.ClosedPullRequestUser
import com.raj.jadon.prasentation.R
import com.raj.jadon.prasentation.common.extension.loadImage
import com.raj.jadon.prasentation.databinding.ClosedPullRequestBinding

class ClosedPullRequestAdapter :
    BaseAdapter<ClosedPullRequestUser, ClosedPullRequestBinding>(object :
        DiffUtil.ItemCallback<ClosedPullRequestUser>() {
        override fun areItemsTheSame(
            oldItem: ClosedPullRequestUser,
            newItem: ClosedPullRequestUser
        ): Boolean {
            return oldItem.user.name == newItem.user.name
        }

        override fun areContentsTheSame(
            oldItem: ClosedPullRequestUser,
            newItem: ClosedPullRequestUser
        ): Boolean {
            return oldItem == newItem
        }

    }, R.layout.trending_rv_item) {
    override fun bind(
        viewBinding: ClosedPullRequestBinding,
        item: ClosedPullRequestUser,
        position: Int
    ) {
        viewBinding.repoImage.loadImage(item.user.avatar_url)
        viewBinding.repoName.text = item.user.name
        viewBinding.repoCreated.text = item.created_date
        viewBinding.repoClosed.text = item.closed_date
    }
}