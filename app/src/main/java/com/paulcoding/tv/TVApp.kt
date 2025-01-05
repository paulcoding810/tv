package com.paulcoding.tv

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.paulcoding.tv.helper.CrashHandler
import com.tencent.mmkv.MMKV

class TVApp : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this

        init()
    }

    private fun init() {
        CrashHandler.install()
        MMKV.initialize(this)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}