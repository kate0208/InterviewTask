package com.kate.interviewtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.kate.interviewtask.database.SourceDao
import com.kate.interviewtask.database.SourceDatabase
import com.kate.interviewtask.model.SourceModel
import com.kate.interviewtask.network.SourceApi
import com.kate.interviewtask.network.SourceService
import kotlinx.coroutines.launch

class FavViewModel(private val dao: SourceDao, private val service: SourceService) : ViewModel() {

    val sourceLivedata = dao.getAllFav()

    fun updateSource() {
        viewModelScope.launch {
            val list = service.getSource()
            dao.insertAll(list)
        }
    }

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
                val service = SourceApi.sourceService
                FavViewModel(dao, service)
            }
        }
    }

}