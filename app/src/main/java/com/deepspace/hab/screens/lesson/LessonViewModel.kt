package com.deepspace.hab.screens.lesson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepspace.hab.models.Lesson
import com.deepspace.hab.models.Module
import com.deepspace.hab.models.ModuleSection
import com.deepspace.hab.screens.home.HomeRepository
import kotlinx.coroutines.launch

/**
 * Created by abhinav on 02/10/21.
 */
class LessonViewModel(private val repo: HomeRepository) : ViewModel() {

    private val _moduleSectionsViewState = MutableLiveData<ModuleSectionsViewState>()
    val moduleSectionsViewState: LiveData<ModuleSectionsViewState> = _moduleSectionsViewState

    var currModule: Module? = null
    var moduleSectionList: MutableLiveData<List<ModuleSection>> = MutableLiveData()
    var lessonDetail: Lesson? = null

    var lessonContent: LessonDetailFragment.LessonContent? = null

    fun fetchModuleSections(moduleId: String) {
        viewModelScope.launch {
            _moduleSectionsViewState.value = ModuleSectionsViewState(showLoader = true)
            _moduleSectionsViewState.value = ModuleSectionsViewState(showLoader = false, repo.fetchModuleSections(moduleId))
        }
    }

}