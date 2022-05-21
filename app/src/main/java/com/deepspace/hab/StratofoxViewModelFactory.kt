package com.deepspace.hab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.deepspace.hab.screens.home.HomeRepository
import com.deepspace.hab.screens.home.HomeViewModel
import com.deepspace.hab.screens.lesson.LessonViewModel

class StratofoxViewModelFactory(private val repo: HomeRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            HomeViewModel::class.java -> HomeViewModel(repo) as T
            LessonViewModel::class.java -> LessonViewModel(repo) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}