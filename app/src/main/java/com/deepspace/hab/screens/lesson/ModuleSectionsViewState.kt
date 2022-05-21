package com.deepspace.hab.screens.lesson

import com.deepspace.hab.models.ModuleSection

data class ModuleSectionsViewState(
    val showLoader: Boolean = false,
    val moduleSections: List<ModuleSection>? = null,
)