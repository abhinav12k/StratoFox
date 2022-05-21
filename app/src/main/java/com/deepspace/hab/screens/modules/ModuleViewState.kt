package com.deepspace.hab.screens.modules

import com.deepspace.hab.models.Module

data class ModuleViewState(val showLoader: Boolean = false, val moduleList: List<Module>? = null)