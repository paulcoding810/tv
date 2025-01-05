package com.paulcoding.tv.helper

import com.paulcoding.tv.BuildConfig


fun log(
    message: Any?,
    tag: String? = "TV",
) {
    if (BuildConfig.DEBUG) {
        val border = "*".repeat(150)
        println("\n")
        println(border)
        print("\t")
        println("$tag:")
        print("\t")
        println(message)
        println(border)
        println("\n")
    }
}

fun <T> T.alsoLog(tag: String? = "HViewer"): T {
    log(this, tag)
    return this
}
