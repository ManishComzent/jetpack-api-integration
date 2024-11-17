package com.example.jetpackwithapiintegration.ui.network

import com.example.jetpackwithapiintegration.ui.model.TicketSubmitModel
import com.example.jetpackwithapiintegration.ui.model.ViewTicketBodyModel
import com.example.jetpackwithapiintegration.ui.model.ViewTicketModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap

interface ApiService {

    @Multipart
    @POST("ticket_apis/ticket_insert.php")
    suspend fun uploadTicketData(
        @PartMap textFields: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part files: MultipartBody.Part?
    ): retrofit2.Response<TicketSubmitModel>

    @Multipart
    @POST("ticket_apis/ticket_view.php")
    suspend fun viewUploadedTickets(
        @PartMap body: Map<String,@JvmSuppressWildcards RequestBody>
    ) : retrofit2.Response<ViewTicketModel>
}