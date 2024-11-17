package com.example.jetpackwithapiintegration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.jetpackwithapiintegration.ui.screens.ViewTicketModelScreen
import com.example.jetpackwithapiintegration.ui.viewmodel.ViewTicketViewModel

class SecondActivity : ComponentActivity() {

    val viewTicketViewModel: ViewTicketViewModel by lazy {
        MyApplication.application.viewTicketViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewTicketModelScreen(viewTicketViewModel)
        }
    }
}