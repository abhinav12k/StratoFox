package com.deepspace.hab.models

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.DocumentSnapshot
import timber.log.Timber
import java.lang.Exception

data class Versions(val moduleListVersion: String? = null, val moduleSectionVersionMap: Map<String,String>? = null){
    companion object{
        fun DocumentSnapshot.toVersions(): Versions?{
            return try {
                val moduleListVersion = getString("moduleListVersion")
                val moduleSectionVersionMap = get("moduleSectionVersionMap") as Map<String,String>
                Versions(moduleListVersion, moduleSectionVersionMap)
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