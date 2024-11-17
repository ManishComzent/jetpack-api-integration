package com.example.jetpackwithapiintegration.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.jetpackwithapiintegration.SecondActivity
import com.example.jetpackwithapiintegration.ui.model.TicketSubmitBodyModel
import com.example.jetpackwithapiintegration.ui.network.NetworkResponse
import com.example.jetpackwithapiintegration.ui.viewmodel.UploadTicketViewModel
import java.io.File

@Composable
fun TicketScreen(viewModel: UploadTicketViewModel) {



    var ticketId by remember { mutableStateOf("2") }
    var forUserId by remember { mutableStateOf("") }
    var moduleId by remember { mutableStateOf("3") }
    var issueTitle by remember { mutableStateOf("Ticket title 6") }
    var issueDescription by remember { mutableStateOf("General Issue6") }
    var userId by remember { mutableStateOf("700032ssssss") }
    var companyId by remember { mutableStateOf("88") }
    var ticketFiles by remember { mutableStateOf<File?>(null) }
    val context = LocalContext.current
    val response = viewModel.response.observeAsState()


    Column(modifier = Modifier.padding(16.dp)) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = ticketId,
            onValueChange = { ticketId = it },
            label = { Text(text = "Ticket Category ID") })
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = forUserId,
            onValueChange = { forUserId = it },
            label = { Text(text = "For User Id") })
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = moduleId,
            onValueChange = { moduleId },
            label = { Text(text = "Module Id") })
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = issueTitle,
            onValueChange = { issueTitle = it },
            label = { Text(text = "Issue Title") })
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = issueDescription,
            onValueChange = { issueDescription = it },
            label = { Text(text = "issue Description") })
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = userId,
            onValueChange = { userId = it },
            label = { Text(text = "User Id") })
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = companyId,
            onValueChange = { companyId = it },
            label = { Text(text = "Company Id") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /* Open file picker and set ticketFile */ }) {
            Text("Select File")
        }

        Button(onClick = {
            viewModel.submitTicket(
                TicketSubmitBodyModel(
                    ticketId,
                    forUserId,
                    moduleId,
                    issueTitle,
                    issueDescription,
                    userId,
                    companyId
                ),
                ticketFile = ticketFiles
            )
        }) {
            Text("Submit")
        }


        when (val result = response.value) {
            is NetworkResponse.Failure -> {
                Text(text = result.message)
            }

            NetworkResponse.Loading -> {
                Text(text = "Wait Searching")
                Toast.makeText(context, "Wait Searching", Toast.LENGTH_SHORT).show()

            }

            is NetworkResponse.Success -> {
                val intent = Intent(context, SecondActivity::class.java)
                context.startActivity(intent)
            }
            null -> {
            }
        }

    }
}