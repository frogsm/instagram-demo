package com.frogsm.instagram_demo.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.displayImage(
    uri: String,
    applyActions: RequestOptions.() -> RequestOptions = { this }
) {

    Glide.with(this)
        .load(uri)
        .apply(applyActions.invoke(RequestOptions()))
        .into(this)
}