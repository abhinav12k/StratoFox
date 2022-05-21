package com.deepspace.hab.models

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.DocumentSnapshot
import timber.log.Timber
import java.lang.Exception

/**
 * Created by abhinav on 27/09/21.
 */
data class ModuleSection(
    override var id: String? = "",
    val lessonId: String? = "",
    val lessonNumber: Int? = 0,
    val moduleNo: Int? = 0,
    val title: String? = "",
    val duration: String? = "",
) : BaseModel() {
    companion object {
        fun DocumentSnapshot.toModuleSection(): ModuleSection? {
            return try {
                val lessonId = getString("lessonId")
                val lessonNumber = getLong("lessonNumber")?.toInt()
                val moduleNo = getLong("moduleNo")?.toInt()
                val duration = getString("duration")
                val title = getString("title")
                ModuleSection(id, lessonId, lessonNumber, moduleNo, title, duration)
            } catch (e: Exception) {
                Timber.e("Error converting Module Section: $e")
                FirebaseCrashlytics.getInstance().log("Error converting module section")
                FirebaseCrashlytics.getInstance().setCustomKey("moduleSectionId", id)
                FirebaseCrashlytics.getInstance().recordException(e)
                null
            }
        }
    }
}