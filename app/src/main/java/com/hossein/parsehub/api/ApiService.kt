package com.hossein.parsehub.api

import com.hossein.parsehub.models.ResponseItems
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    // TODO:  
    // Item List
    @GET("feed")
    suspend fun getItemsList(): Response<ResponseItems>

}