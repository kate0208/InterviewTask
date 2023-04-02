package com.kate.interviewtask.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.kate.interviewtask.database.SourceDao
import com.kate.interviewtask.database.SourceDatabase
import com.kate.interviewtask.fragment.InfoFragmentArgs
import com.kate.interviewtask.model.SourceModel

class InfoViewModel(savedStateHandle: SavedStateHandle, dao: SourceDao) : ViewModel() {

    private val primaryKey = InfoFragmentArgs.fromSavedStateHandle(savedStateHandle).primaryKey
    val source: LiveData<SourceModel> = dao.get(primaryKey)

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    requireNotNull(this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                val dao = SourceDatabase.getInstance(application).sourceDao
                val savedStateHandle = createSavedStateHandle()

                InfoViewModel(savedStateHandle, dao)
            }
        }
    }
}