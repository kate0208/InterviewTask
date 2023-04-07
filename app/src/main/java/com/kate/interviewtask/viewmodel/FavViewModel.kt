package com.kate.interviewtask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.kate.interviewtask.database.SourceDao
import com.kate.interviewtask.database.SourceDatabase
import com.kate.interviewtask.model.SourceModel
import kotlinx.coroutines.launch

class FavViewModel(private val dao: SourceDao) : ViewModel() {

    val sourceFlow = Pager(
        PagingConfig(pageSize = 20)
    ) {
        dao.getAllFav()
    }.flow
        .cachedIn(viewModelScope)

    fun updateFav(sourceModel: SourceModel) {
        viewModelScope.launch {
            val favValue = sourceModel.copy(
                fav = !sourceModel.fav
            )
            dao.update(favValue)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = requireNotNull(this[APPLICATION_KEY])
                val dao = SourceDatabase.getInstance(application).sourceDao
                FavViewModel(dao)
            }
        }
    }

}