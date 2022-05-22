package com.deepspace.hab

import android.app.Application
import com.deepspace.hab.screens.home.HomeRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import timber.log.Timber

/**
 * Created by abhinav on 25/09/21.
 */
class Stratofox : Application() {

    private val db by lazy { Firebase.firestore }
    val homeRepository: HomeRepository by lazy { HomeRepository(db) }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}