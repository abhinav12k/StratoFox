package com.deepspace.hab.models

import android.os.Parcelable
import com.deepspace.hab.models.BaseModel
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.parcelize.Parcelize
import timber.log.Timber
import java.lang.Exception

/**
 * Created by abhinav on 26/09/21.
 */
@Parcelize
data class Module(
    override var id: String? = "",
    val description: String? = "",
    val moduleDuration: String? = "",
    val imageId: String? = "",
    val noOfLessons: Int? = 0,
    val rank: Int? = 0,  //to determine position
    val title: String? = "",
    val moduleSectionVersion: String? = "",   //Not necessary
) : BaseModel(), Parcelable {
    companion object {
        fun DocumentSnapshot.toModule(): Module? {
            try {
                val description = getString("description")
                val moduleDuration = getString("moduleDuration")
                val imageId = getString("imageId")
                val noOfLessons = getLong("noOfLessons")?.toInt()
                val rank = getLong("rank")?.toInt()
                val title = getString("title")
                val moduleSectionVersion = getString("moduleSectionVersion")
                return Module(id, description, moduleDuration, imageId, noOfLessons, rank, title, moduleSectionVersion)
            } catch (e: Exception) {
                Timber.e("Error converting module: $e")
                FirebaseCrashlytics.getInstance().log("Error converting module")
                FirebaseCrashlytics.getInstance().setCustomKey("moduleId", id)
                FirebaseCrashlytics.getInstance().recordException(e)
                return null
            }
        }
    }
}