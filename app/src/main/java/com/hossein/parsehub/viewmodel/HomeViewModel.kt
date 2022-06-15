package com.hossein.parsehub.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hossein.parsehub.models.ResponseItems
import com.hossein.parsehub.repository.HomeRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    val itemsList =MutableLiveData<ResponseItems>()

    fun loadItems() = viewModelScope.launch {
        loading.postValue(true)
        val respose = repository.itemsList()
        if (respose.isSuccessful) {
            itemsList.postValue(respose.body())
        }
        loading.postValue(false)
    }

}