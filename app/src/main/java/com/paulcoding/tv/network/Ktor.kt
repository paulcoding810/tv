package com.paulcoding.tv.network

import com.google.gson.Strictness
import com.paulcoding.tv.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.gson.gson

val ktorClient
    get() = HttpClient(Android) {
        install(ContentNegotiation) {
            gson {
                setStrictness(Strictness.LENIENT)
            }
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    com.paulcoding.tv.helper.log(message, "HTTP Client")
                }
            }
            level = if (BuildConfig.DEBUG) LogLevel.HEADERS else LogLevel.INFO
        }
    }
