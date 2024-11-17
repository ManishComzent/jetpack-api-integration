package com.example.jetpackwithapiintegration.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackwithapiintegration.ui.model.TicketSubmitBodyModel
import com.example.jetpackwithapiintegration.ui.model.TicketSubmitModel
import com.example.jetpackwithapiintegration.ui.network.NetworkResponse
import com.example.jetpackwithapiintegration.ui.network.repository.TicketRepository
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class UploadTicketViewModel(private val ticketRepository: TicketRepository) : ViewModel() {

    val _response = MutableLiveData<NetworkResponse<TicketSubmitModel>>()
    val response: LiveData<NetworkResponse<TicketSubmitModel>> = _response


    fun submitTicket(
        textFieldParts: TicketSubmitBodyModel,
        ticketFile: File?
    ) {
        val filePart = createFilePart(ticketFile, "ticket_files1")
        _response.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response =
                    ticketRepository.submitTicketData(textFieldParts.toRequestBodyMap(), filePart)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _response.value = NetworkResponse.Success(it)
                    }
                    Log.i("TAG", "submitTicket Data : true")
                } else {
                    _response.value = NetworkResponse.Failure("Response is False")
                    Log.i("TAG", "submitTicket Data : false")
                }
            } catch (e: Exception) {
                _response.value = NetworkResponse.Failure("Response occured Exception")
                e.printStackTrace()
            }
        }
    }


    fun createFilePart(file: File?, paramName: String): MultipartBody.Part? {
        return file?.let {
            val requestFile = RequestBody.create("application/octet-stream".toMediaTypeOrNull(), it)
            MultipartBody.Part.createFormData(paramName, it.name, requestFile)
        }
    }
}