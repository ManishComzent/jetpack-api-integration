package com.example.jetpackwithapiintegration.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackwithapiintegration.ui.model.TicketResponse
import com.example.jetpackwithapiintegration.ui.network.NetworkResponse
import com.example.jetpackwithapiintegration.ui.viewmodel.ViewTicketViewModel

@Composable
fun ViewTicketModelScreen(viewTicketViewModel: ViewTicketViewModel) {
    val context = LocalContext.current
    val response = viewTicketViewModel.networkResponse.observeAsState()

    LaunchedEffect(Unit) {
        viewTicketViewModel.viewTicketData()
    }

    when (val result = response.value) {
        is NetworkResponse.Failure -> {
            Toast.makeText(context, result.message ?: "An error occurred", Toast.LENGTH_SHORT).show()
        }

        NetworkResponse.Loading -> {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }

        is NetworkResponse.Success -> {
            val data = result.data.data
            if (data.isNullOrEmpty()) {
                Text(text = "No tickets available", modifier = Modifier.padding(16.dp))
            } else {
                commonCardView(data)
            }
        }

        null -> {
            Text(text = "No data available", modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun commonCardView(dataList: List<TicketResponse>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(dataList) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp), // Adjust padding for better spacing
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = item.ticketRefNo, fontSize = 16.sp)
                }
            }
        }
    }
}
