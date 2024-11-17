package com.example.jetpackwithapiintegration.ui.model

import com.google.gson.annotations.SerializedName

data class TicketResponse(
    @SerializedName("ticket_refno")
    val ticketRefNo : String
)
