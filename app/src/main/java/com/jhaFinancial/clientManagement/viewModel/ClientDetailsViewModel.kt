package com.jhaFinancial.clientManagement.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.jhaFinancial.clientManagement.model.ClientDetails
import com.jhaFinancial.clientManagement.repository.ClientDetailsRepository

class ClientDetailsViewModel(application: Application) : AndroidViewModel(application) {
    val showCLientDetails: LiveData<List<ClientDetails>>
    val showProgress: LiveData<Boolean>
    private val repository = ClientDetailsRepository(application)

    init {
        this.showCLientDetails = repository.showClientDetails
        this.showProgress = repository.showProgress
    }

    fun getClientDetails() {
        repository.getClientDetails()
    }
}