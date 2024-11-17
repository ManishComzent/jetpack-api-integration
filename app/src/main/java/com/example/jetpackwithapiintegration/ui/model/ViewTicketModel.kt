package com.example.jetpackwithapiintegration.ui.model

import androidx.core.app.NotificationCompat.MessagingStyle.Message

data class ViewTicketModel(
    val status : Boolean,
    val message: String,
    val data : List<TicketResponse>
)
