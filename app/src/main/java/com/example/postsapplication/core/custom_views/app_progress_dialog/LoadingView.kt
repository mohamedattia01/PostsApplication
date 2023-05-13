package com.example.postsapplication.core.custom_views.app_progress_dialog

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.postsapplication.R

class LoadingView : FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        inflate(context, R.layout.loading_view, this)
    }
}