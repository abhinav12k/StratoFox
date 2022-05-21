package com.deepspace.hab.screens.home

import com.deepspace.hab.constants.StratofoxFirebaseConstants
import com.deepspace.hab.models.Module
import com.deepspace.hab.models.Module.Companion.toModule
import com.deepspace.hab.models.ModuleSection
import com.deepspace.hab.models.ModuleSection.Companion.toModuleSection
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class HomeRepository {

    private val db = Firebase.firestore

    suspend fun fetchModuleList(): MutableList<Module> {
        val moduleList = mutableListOf<Module>()
        db.collection(StratofoxFirebaseConstants.MODULE_LIST_COLLECTION)
            .get().await().mapNotNull { snapshot ->
                snapshot.toModule()?.let { moduleList.add(it) }
            }
        return moduleList
    }

    suspend fun fetchModuleSections(moduleId: String): MutableList<ModuleSection>{
        val moduleSections = mutableListOf<ModuleSection>()
        db.collection(StratofoxFirebaseConstants.MODULE_LIST_COLLECTION)
            .document(moduleId)
            .collection(StratofoxFirebaseConstants.MODULE_SECTIONS_COLLECTION)
            .get().await().mapNotNull { snapshot ->
                snapshot.toModuleSection()?.let { moduleSections.add(it) }
            }
        return moduleSections
    }

}