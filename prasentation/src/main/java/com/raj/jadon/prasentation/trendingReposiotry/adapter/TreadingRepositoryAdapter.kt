package com.raj.jadon.prasentation.trendingReposiotry.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.application.common.BaseAdapter
import com.raj.jadon.prasentation.R
import com.raj.jadon.prasentation.databinding.TrendingRvItemBinding

class TreadingRepositoryAdapter :
    BaseAdapter<String, TrendingRvItemBinding>(object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }, R.layout.trending_rv_item) {
    override fun bind(viewBinding: TrendingRvItemBinding, item: String, position: Int) {}
}