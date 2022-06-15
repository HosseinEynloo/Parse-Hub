package com.hossein.parsehub.repository


import com.hossein.parsehub.api.ApiService
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: ApiService) {

    suspend fun itemsList() = api.getItemsList()

}