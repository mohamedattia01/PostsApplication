package com.example.postsapplication.core.data.locale.shared_preference

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferenceManager @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun putSharedPrefValues(vararg entries: Pair<String, Any>) {
        sharedPreferences.edit().apply {
            entries.forEach { (key, value) ->
                putItem(key, value)
            }
        }.apply()
    }

    private fun SharedPreferences.Editor.putItem(key: String, value: Any) {
        when (value) {
            is String -> putString(key, value)
            is Boolean -> putBoolean(key, value)
            else -> throw IllegalArgumentException("${value.javaClass} is not supported")
        }
    }

    fun getString(key: String, defaultValue: String? = null) =
        sharedPreferences.getString(key, defaultValue)

    fun getBoolean(key: String, defaultValue: Boolean = false) =
        sharedPreferences.getBoolean(key, defaultValue)
}