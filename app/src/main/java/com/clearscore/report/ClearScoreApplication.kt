package com.clearscore.report

import android.app.Application
import com.clearscore.report.BuildConfig.DEBUG
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ClearScoreApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        if (DEBUG) {
            //Timber.plant(Timber.DebugTree())
        }
    }
}