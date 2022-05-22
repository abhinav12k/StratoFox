package com.deepspace.hab.models

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.DocumentSnapshot
import timber.log.Timber
import java.lang.Exception

data class ModuleListVersion(val version: String? = null){
    companion object{
        fun DocumentSnapshot.toModuleListVersion(): ModuleListVersion?{
            return try {
                val v = getString("moduleListVersion")
                ModuleListVersion(v)
            }catch (e: Exception){
                Timber.e("Error converting version: $e")
                FirebaseCrashlytics.getInstance().log("Error converting version")
                FirebaseCrashlytics.getInstance().setCustomKey("version document id", id)
                FirebaseCrashlytics.getInstance().recordException(e)
                null
            }
        }
    }
}