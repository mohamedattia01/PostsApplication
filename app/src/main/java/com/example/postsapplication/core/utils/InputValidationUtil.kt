package com.example.postsapplication.core.utils

import java.util.regex.Pattern

class InputValidationUtil {

    companion object {
        fun isUserNameValid(
            userNameTextInput: String
        ): Boolean {
            val regexCheck = userNameTextInput.trim { it <= ' ' }
            val regex = Pattern.compile("^([._-](?![._-])|[a-zA-Z0-9]){5,55}\$")
            return regex.matcher(regexCheck).find()
        }

        fun isEmailValid(userEmailInput: String): Boolean {
            val regexCheck = userEmailInput.trim { it <= ' ' }
            val regex = Pattern.compile("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")
            return regex.matcher(regexCheck).find()
        }

        fun isPasswordValid(
            passwordTextInput: String
        ): Boolean {
            val regexCheck = passwordTextInput.trim { it <= ' ' }
            return regexCheck.length in 8..15
        }
    }
}