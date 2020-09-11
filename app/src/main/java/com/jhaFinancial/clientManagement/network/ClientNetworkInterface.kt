package com.jhaFinancial.clientManagement.network

import com.jhaFinancial.clientManagement.model.ClientDetails
import retrofit2.Call
import retrofit2.http.GET

interface ClientNetworkInterface {
    @GET("getAll")
    fun getClientDetails(): Call<List<ClientDetails>>
}