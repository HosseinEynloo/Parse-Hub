package com.hossein.parsehub.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.hossein.parsehub.paging.ItemsPagingSource
import com.hossein.parsehub.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModelPaging @Inject constructor(private val repository: HomeRepository) :
    ViewModel() {

    val itemList = Pager(PagingConfig(1)) {
        ItemsPagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}