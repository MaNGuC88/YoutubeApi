package com.example.youtubeapi.extensions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

var View.invisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.INVISIBLE
    }

fun ImageView.load (url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}


