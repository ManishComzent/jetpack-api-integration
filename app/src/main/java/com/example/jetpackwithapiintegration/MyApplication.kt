package com.example.jetpackwithapiintegration

import android.app.Application
import com.example.jetpackwithapiintegration.ui.network.repository.TicketRepository
import com.example.jetpackwithapiintegration.ui.viewmodel.UploadTicketViewModel
import com.example.jetpackwithapiintegration.ui.viewmodel.ViewTicketViewModel

class MyApplication : Application() {
    private val ticketRepository by lazy { TicketRepository() }

    val uploadTicketViewModel by lazy { UploadTicketViewModel(ticketRepository = ticketRepository) }
    val viewTicketViewModel by lazy { ViewTicketViewModel(ticketRepository = ticketRepository) }

    companion object {
        lateinit var application: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}