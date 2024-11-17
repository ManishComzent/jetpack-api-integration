package com.example.jetpackwithapiintegration.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackwithapiintegration.ui.model.ViewTicketBodyModel
import com.example.jetpackwithapiintegration.ui.model.ViewTicketModel
import com.example.jetpackwithapiintegration.ui.network.NetworkResponse
import com.example.jetpackwithapiintegration.ui.network.repository.TicketRepository
import kotlinx.coroutines.launch

class ViewTicketViewModel(val ticketRepository: TicketRepository) : ViewModel() {
    val _networkRespose = MutableLiveData<NetworkResponse<ViewTicketModel>>()
    val networkResponse: LiveData<NetworkResponse<ViewTicketModel>> = _networkRespose


    fun viewTicketData() {
        _networkRespose.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val viewTicketBodyModel : ViewTicketBodyModel = ViewTicketBodyModel("237", "700372")
                val response = ticketRepository.viewUploadedTicket(viewTicketBodyModel.toRequestBodyMap())
                if (response.isSuccessful){
                    response.body()?.let {
                        _networkRespose.value = NetworkResponse.Success(it)
                    }
                }else {
                    _networkRespose.value = NetworkResponse.Failure("Response Fail")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _networkRespose.value = NetworkResponse.Failure("Response Exception Fail")
            }
        }
    }
}