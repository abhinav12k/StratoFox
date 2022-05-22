package com.deepspace.hab.screens.home

import com.deepspace.hab.constants.StratofoxFirebaseConstants
import com.deepspace.hab.models.Module
import com.deepspace.hab.models.Module.Companion.toModule
import com.deepspace.hab.models.ModuleSection
import com.deepspace.hab.models.ModuleSection.Companion.toModuleSection
import com.deepspace.hab.models.ModuleListVersion.Companion.toModuleListVersion
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class HomeRepository(
    private val db: FirebaseFirestore
) {

    suspend fun fetchModuleList(): MutableList<Module> {
        val moduleList = mutableListOf<Module>()
        val collectionRef = getCollectionRef(StratofoxFirebaseConstants.MODULE_LIST_COLLECTION)

        if(shouldFetchModuleList()){
            Timber.tag("abhinav").d("fetching because remote version is updated!")
            collectionRef.get(Source.SERVER).await().mapNotNull { snapshot -> snapshot.toModule()?.let { moduleList.add(it) } }
        }else {
            Timber.tag("abhinav").d("fetch from cache or server remote said data is not updated!")
            val resultSnapshot = collectionRef.get(Source.CACHE).await()
                .let { if (it.isEmpty) collectionRef.get(Source.SERVER).await() else it }
            resultSnapshot.mapNotNull { snapshot ->
                snapshot.toModule()?.let { moduleList.add(it) }
            }
        }
        return moduleList
    }

    private suspend fun shouldFetchModuleList(): Boolean {
        val moduleListVersionCollection = getCollectionRef(StratofoxFirebaseConstants.VERSIONS_COLLECTION)
        val moduleVersionCached = moduleListVersionCollection.get(Source.CACHE).await()
        return if(moduleVersionCached.isEmpty) {
            moduleListVersionCollection.get(Source.SERVER).await()
            true
        }else{
            val firebaseVersion = moduleListVersionCollection.get(Source.SERVER).await().mapNotNull { it.toModuleListVersion()?.version?.toLong() }
            val cachedVersion = moduleVersionCached.mapNotNull { it.toModuleListVersion()?.version?.toLong() }

            cachedVersion.isNotEmpty() && firebaseVersion.isNotEmpty() && cachedVersion[0] < firebaseVersion[0]
        }
    }

    suspend fun fetchModuleSections(moduleId: String): MutableList<ModuleSection> {
        val moduleSections = mutableListOf<ModuleSection>()
        val subCollectionRef = getSubCollectionRef(
            StratofoxFirebaseConstants.MODULE_LIST_COLLECTION,
            moduleId,
            StratofoxFirebaseConstants.MODULE_SECTIONS_COLLECTION
        )
        val resultSnapshot = subCollectionRef.get(Source.CACHE).await()
            .let { if (it.isEmpty) subCollectionRef.get(Source.SERVER).await() else it }
        resultSnapshot.mapNotNull { snapshot ->
            snapshot.toModuleSection()?.let { moduleSections.add(it) }
        }
        return moduleSections
    }

    private fun getSubCollectionRef(
        parentCollection: String,
        documentId: String,
        subCollection: String
    ): CollectionReference {
        return getCollectionRef(parentCollection).document(documentId).collection(subCollection)
    }

    private fun getCollectionRef(collectionName: String): CollectionReference {
        return db.collection(collectionName)
    }
}