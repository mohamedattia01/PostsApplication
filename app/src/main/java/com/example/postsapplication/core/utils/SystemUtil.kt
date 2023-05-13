package com.example.postsapplication.core.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

class SystemUtil {
    companion object {
        fun hideKeyboard(activity: Activity) {
            try {
                val imm =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
            } catch (e: NullPointerException) {

            }
        }
    }
}