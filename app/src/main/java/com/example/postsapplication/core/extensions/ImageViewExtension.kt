package com.example.postsapplication.core.extensions

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

@SuppressLint("CheckResult")
fun ImageView.loadImage(
    imageUrl: String? = null,
    gifRaw: Int? = null,
    placeholderDrawable: Drawable? = null,
    errorDrawable: Drawable? = null,
    fallbackDrawable: Drawable? = null,
    skipMemoryCache: Boolean = false,
    skipDiskCache: Boolean = false,
    isCircularImage: Boolean = false
) {

    val imageLoader = Glide.with(context)
        .load(imageUrl?:gifRaw)
        .placeholder(placeholderDrawable)
        .error(errorDrawable)
        .fallback(fallbackDrawable)

    if (isCircularImage) imageLoader.apply(RequestOptions().circleCrop())

    if (skipDiskCache) imageLoader.diskCacheStrategy(DiskCacheStrategy.NONE)

    if (skipMemoryCache) imageLoader.skipMemoryCache(true)

    imageLoader.into(this)
}