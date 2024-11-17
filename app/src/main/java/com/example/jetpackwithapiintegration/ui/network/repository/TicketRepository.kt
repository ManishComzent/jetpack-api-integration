package com.example.jetpackwithapiintegration.ui.network.repository

import com.example.jetpackwithapiintegration.ui.model.TicketSubmitModel
import com.example.jetpackwithapiintegration.ui.model.ViewTicketBodyModel
import com.example.jetpackwithapiintegration.ui.model.ViewTicketModel
import com.example.jetpackwithapiintegration.ui.network.ApiService
import com.example.jetpackwithapiintegration.ui.network.RetrofitInstance
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class TicketRepository {
    suspend fun submitTicketData(textFields: Map<String, @JvmSuppressWildcards RequestBody>, files: MultipartBody.Part?) : Response<TicketSubmitModel> {
        val ticketResponse = RetrofitInstance.apiService.uploadTicketData(textFields = textFields, files = files)
        return ticketResponse
    }

    suspend fun viewUploadedTicket(body: Map<String,@JvmSuppressWildcards RequestBody>) : Response<ViewTicketModel>{
        val viewResponse = RetrofitInstance.apiService.viewUploadedTickets(body)
        return viewResponse
    }

}