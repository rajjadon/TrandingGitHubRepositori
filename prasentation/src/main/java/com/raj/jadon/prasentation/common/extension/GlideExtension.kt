package com.raj.jadon.prasentation.common.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?) {
    url?.let {
        Glide.with(this.context)
            .load(it)
            .circleCrop()
            .into(this)
    }
}