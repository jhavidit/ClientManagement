package com.jhaFinancial.clientManagement.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jhaFinancial.clientManagement.model.ClientDetails
import com.jhaFinancial.clientManagement.network.ClientNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClientDetailsRepository(val application: Application) {
    val showClientDetails = MutableLiveData<List<ClientDetails>>()
    val showProgress = MutableLiveData<Boolean>()

    fun getClientDetails() {
        val retrofitService = ClientNetwork.getClient(application)
        val callApi = retrofitService.getClientDetails()
        showProgress.value = true
        callApi.enqueue(object : Callback<List<ClientDetails>> {
            override fun onFailure(call: Call<List<ClientDetails>>, t: Throwable) {
                showProgress.value = false
                Log.d("error", t.message)

            }

            override fun onResponse(
                call: Call<List<ClientDetails>>,
                response: Response<List<ClientDetails>>
            ) {
                showProgress.value = false
                showClientDetails.value = response.body()
                Log.d("success", response.toString())
                Log.d("body", response.body().toString())
            }

        })

    }
}