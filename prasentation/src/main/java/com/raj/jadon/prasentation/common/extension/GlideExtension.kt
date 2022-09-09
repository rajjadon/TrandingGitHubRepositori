package com.ssf.homevisit.newModule.common.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

fun ImageView.loadImage(url: String?, cornerRadius: Int) {
    url?.let {
        Glide.with(this.context)
            .load(it)
            .transform(CenterInside(), RoundedCorners(cornerRadius))
            .into(this)
    }
}