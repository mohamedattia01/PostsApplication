package com.example.postsapplication.core.custom_views.app_progress_dialog

import android.app.Dialog
import android.content.Context
import android.view.ViewGroup

class AppProgressDialog(context: Context) : Dialog(context, android.R.style.ThemeOverlay) {

    fun showProgress(): AppProgressDialog {
        if (!this.isShowing) {
            this.setCancelable(false)
            /* The next line will add the ProgressBar to the dialog. */
            val loadingBarView = LoadingView(context)
            this.addContentView(
                loadingBarView,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
            this.setCanceledOnTouchOutside(false)
            try {
                this.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return this
    }
}