package com.kate.interviewtask.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.kate.interviewtask.database.SourceDao
import com.kate.interviewtask.database.SourceDatabase
import com.kate.interviewtask.fragment.InfoFragmentArgs
import com.kate.interviewtask.model.SourceModel
import kotlinx.coroutines.launch

class InfoViewModel(savedStateHandle: SavedStateHandle, private val dao: SourceDao) : ViewModel() {

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

    fun updateFav() {
        viewModelScope.launch {
            val sourceModel = source.value ?: return@launch
            val favValue = sourceModel.copy(
                fav = !sourceModel.fav
            )
            dao.update(favValue)
        }
    }
}