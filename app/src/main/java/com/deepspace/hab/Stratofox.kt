package com.deepspace.hab

import android.app.Application
import com.deepspace.hab.screens.HomeRepository
import timber.log.Timber

/**
 * Created by abhinav on 25/09/21.
 */
class Stratofox : Application() {

    val homeRepository: HomeRepository by lazy { HomeRepository() }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}