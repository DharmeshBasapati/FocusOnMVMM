package com.app.focusonmvmm.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.focusonmvmm.data.api.FocusApiHelper
import com.app.focusonmvmm.data.repository.FocusRepository
import com.app.focusonmvmm.ui.main.viewmodel.FocusViewModel

class FocusViewModelFactory(private val focusApiHelper: FocusApiHelper):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       if(modelClass.isAssignableFrom(FocusViewModel::class.java)){
            return FocusViewModel(FocusRepository(focusApiHelper)) as T
       }
        throw IllegalArgumentException("Unknown Class Name")
    }
}