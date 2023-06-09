package com.example.postsapplication.core.custom_views.blur

import android.graphics.Bitmap


internal class NoOpBlurAlgorithm : BlurAlgorithm {
    override fun blur(bitmap: Bitmap?, blurRadius: Float): Bitmap? {
        return bitmap
    }

    override fun destroy() {}
    override fun canModifyBitmap(): Boolean {
        return true
    }

    override fun getSupportedBitmapConfig(): Bitmap.Config {
        return Bitmap.Config.ARGB_8888
    }
}