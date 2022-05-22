package com.deepspace.hab.screens.home

import com.deepspace.hab.constants.StratofoxFirebaseConstants
import com.deepspace.hab.models.Module
import com.deepspace.hab.models.Module.Companion.toModule
import com.deepspace.hab.models.ModuleSection
import com.deepspace.hab.models.ModuleSection.Companion.toModuleSection
import com.deepspace.hab.models.Versions.Companion.toVersions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class HomeRepository(
    private val db: FirebaseFirestore,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    private var firebaseModuleListVersion: String? = null
    private var firebaseModuleSectionVersionMap: Map<String, String>? = null

    suspend fun fetchModuleList(): MutableList<Module> {
        if(firebaseModuleListVersion == null)
            fetchVersionInfo()
        val moduleList = mutableListOf<Module>()
        val collectionRef = getCollectionRef(StratofoxFirebaseConstants.MODULE_LIST_COLLECTION)

        if (shouldFetchModuleList()) {
            Timber.tag("abhinav").d("fetching because remote version is updated!")
            collectionRef.get(Source.SERVER).await().mapNotNull { snapshot -> snapshot.toModule()?.let { moduleList.add(it) } }
        } else {
            Timber.tag("abhinav").d("fetch from cache or server as there is no update in remote data")
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
        return if (moduleVersionCached.isEmpty) {
            fetchVersionInfo()
            true
        } else {
            val cachedVersion = moduleVersionCached.mapNotNull { it.toVersions()?.moduleListVersion?.toLong() }
            cachedVersion.isNotEmpty() && cachedVersion[0] < firebaseModuleListVersion?.toLong() ?: return true
        }
    }

    suspend fun fetchModuleSections(
        moduleId: String,
        moduleSectionVersion: String?
    ): MutableList<ModuleSection> {
        if(firebaseModuleSectionVersionMap == null)
            fetchVersionInfo()

        val moduleSections = mutableListOf<ModuleSection>()
        val subCollectionRef = getSubCollectionRef(
            StratofoxFirebaseConstants.MODULE_LIST_COLLECTION,
            moduleId,
            StratofoxFirebaseConstants.MODULE_SECTIONS_COLLECTION
        )
        if (shouldFetchModuleSections(moduleId, moduleSectionVersion)) {
            Timber.tag("abhinav").d("fetching module sections because remote version is updated!")
            subCollectionRef.get(Source.SERVER).await().mapNotNull { snapshot -> snapshot.toModuleSection()?.let { moduleSections.add(it) } }
        } else {
            Timber.tag("abhinav").d("fetch module sections from cache or server as there is no update in remote data")
            val resultSnapshot = subCollectionRef.get(Source.CACHE).await()
                .let { if (it.isEmpty) subCollectionRef.get(Source.SERVER).await() else it }
            resultSnapshot.mapNotNull { snapshot ->
                snapshot.toModuleSection()?.let { moduleSections.add(it) }
            }
        }
        return moduleSections
    }

    private fun shouldFetchModuleSections(
        moduleId: String,
        moduleSectionVersion: String?
    ): Boolean {
        val moduleSectionVersionLong = moduleSectionVersion?.toLong() ?: return false
        val firebaseVersion = firebaseModuleSectionVersionMap?.get(moduleId)?.toLong() ?: return false
        if(moduleSectionVersionLong < firebaseVersion)
            return true
        return false
    }

    private suspend fun fetchVersionInfo() {
        val versions = getCollectionRef(StratofoxFirebaseConstants.VERSIONS_COLLECTION).get().await().mapNotNull { it.toVersions() }[0]
        firebaseModuleListVersion = versions.moduleListVersion
        firebaseModuleSectionVersionMap = versions.moduleSectionVersionMap
        Timber.tag("abhinav").d("$firebaseModuleSectionVersionMap")
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