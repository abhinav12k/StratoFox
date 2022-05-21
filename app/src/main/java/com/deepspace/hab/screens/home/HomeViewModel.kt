package com.deepspace.hab.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepspace.hab.screens.modules.ModuleViewState
import com.deepspace.hab.utils.SingleLiveEvent
import kotlinx.coroutines.launch

/**
 * Created by abhinav on 26/09/21.
 */
class HomeViewModel(private val repo: HomeRepository): ViewModel() {

    private val _moduleViewState = SingleLiveEvent<ModuleViewState>()
    val moduleViewState: LiveData<ModuleViewState> = _moduleViewState

    fun fetchModuleList() {
        viewModelScope.launch {
            _moduleViewState.value = ModuleViewState(showLoader = true)
            _moduleViewState.value = ModuleViewState(showLoader = false, repo.fetchModuleList())
        }
    }

}