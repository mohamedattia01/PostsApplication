package com.example.postsapplication.core.extensions

import android.widget.EditText
import androidx.core.widget.doOnTextChanged

fun EditText.doOnTextChanged(
    action: (
        text: CharSequence?,
        start: Int,
        before: Int,
        count: Int,
    ) -> Unit
) {
    this.doOnTextChanged(action)
}