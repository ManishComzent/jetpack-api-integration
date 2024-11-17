package com.example.jetpackwithapiintegration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.jetpackwithapiintegration.ui.screens.TicketScreen
import com.example.jetpackwithapiintegration.ui.theme.JetPackWithApiIntegrationTheme
import com.example.jetpackwithapiintegration.ui.viewmodel.UploadTicketViewModel

class MainActivity : ComponentActivity() {

    private val uploadTicketViewModel: UploadTicketViewModel by lazy {
        (application as MyApplication).uploadTicketViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackWithApiIntegrationTheme {

                TicketScreen(uploadTicketViewModel)
            }
        }
    }
}