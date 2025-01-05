package com.paulcoding.tv.helper

import android.widget.Toast
import androidx.annotation.StringRes
import com.paulcoding.tv.TVApp

fun makeToast(message: String?) {
    if (message == null) return

    Toast.makeText(
        TVApp.context,
        message,
        Toast.LENGTH_SHORT
    ).show()
}

fun makeToast(@StringRes stringId: Int) {
    Toast.makeText(
        TVApp.context,
        TVApp.context.getString(stringId),
        Toast.LENGTH_SHORT
    ).show()
}