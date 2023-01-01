package com.eddy.myexam.base

import android.app.Application
import com.eddy.myexam.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(e: StackTraceElement): String {
                    return "(${e.fileName}:${e.lineNumber})#${e.methodName}|${Thread.currentThread().name}"
                }
            })
        }
    }
}