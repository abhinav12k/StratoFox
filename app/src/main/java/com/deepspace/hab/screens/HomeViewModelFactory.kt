package com.deepspace.hab.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewModelFactory(private val repo: HomeRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repo) as T
    }
}