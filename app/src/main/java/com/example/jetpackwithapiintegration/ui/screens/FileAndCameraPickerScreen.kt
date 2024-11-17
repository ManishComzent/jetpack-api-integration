package com.example.jetpackwithapiintegration.ui.screens

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

//@Composable
//fun FileAndCameraPickerScreen() {
//    val context = LocalContext.current
//    var selectedFiles by remember { mutableStateOf<List<Uri>>(emptyList()) }
//    var cameraImageUri by remember { mutableStateOf<Uri?>(null) }
//
//    // File Picker launcher
//    val filePickerLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetMultipleContents()
//    ) { uris ->
//        selectedFiles = uris
//    }
//
//    // Camera launcher
//    val cameraLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.TakePicture()
//    ) { isSuccess ->
//        if (isSuccess) {
//            // Handle the image Uri from camera capture
//        }
//    }
//
//    // Create a file to save the camera image
//    fun createImageFile(context: Context): Uri? {
//        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
//        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//        val imageFile = File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
//        return Uri.fromFile(imageFile)
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        // Button to pick multiple files
//        Button(onClick = {
//            filePickerLauncher.launch("*/*") // Allows selecting all file types. Use specific types for restrictions.
//        }) {
//            Text("Select Files (PDF, Excel, Images, etc.)")
//        }
//
//        // Button to capture an image
//        Button(onClick = {
//            val uri = createImageFile(context)
//            cameraImageUri = uri
//            uri?.let {
//                cameraLauncher.launch(it)
//            }
//        }) {
//            Text("Capture Image")
//        }
//
//        // Display selected files
//        Text("Selected Files:")
//        LazyColumn {
//            items(selectedFiles) { uri ->
//                FilePreview(uri = uri, context = context)
//            }
//        }
//
//        // Display captured image URI
//        cameraImageUri?.let {
//            Text("Captured Image URI:")
//            Image(
//                painter = rememberAsyncImagePainter(model = it),
//                contentDescription = "Captured Image",
//                modifier = Modifier
//                    .height(200.dp)
//                    .fillMaxWidth()
//                    .clip(RoundedCornerShape(8.dp))
//                    .border(2.dp, Color.Gray, RoundedCornerShape(8.dp))
//            )
//        }
//    }
//}
//
//@Composable
//fun FilePreview(uri: Uri, context: Context) {
//    val mimeType = context.contentResolver.getType(uri)
//    when {
//        mimeType?.startsWith("image/") == true -> {
//            Image(
//                painter = rememberAsyncImagePainter(model = uri),
//                contentDescription = "Image Preview",
//                modifier = Modifier
//                    .height(100.dp)
//                    .fillMaxWidth()
//                    .clip(RoundedCornerShape(8.dp))
//                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
//            )
//        }
//        mimeType == "application/pdf" -> {
//            Text("PDF File: $uri", style = MaterialTheme.typography.bodyLarge)
//        }
//        mimeType == "application/vnd.ms-excel" || mimeType == "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" -> {
//            Text("Excel File: $uri", style = MaterialTheme.typography.bodyLarge)
//        }
//        else -> {
//            Text("File: $uri", style = MaterialTheme.typography.bodyLarge)
//        }
//    }
//}

@Composable
fun FileAndCameraPickerScreen() {
    val context = LocalContext.current
    var selectedFiles by remember { mutableStateOf<List<Uri>>(emptyList()) }
    var cameraImageUri by remember { mutableStateOf<Uri?>(null) }

    // File Picker launcher for multiple files
    val multipleFilePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris ->
        selectedFiles = uris
    }

    // Camera launcher
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            // Handle the image Uri from camera capture
        }
    }

    // Create a file to save the camera image
    fun createImageFile(context: Context): Uri? {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageFile = File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
        return Uri.fromFile(imageFile)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Button to pick multiple files (PDF, Excel, Images, etc.)
        Button(onClick = {
            multipleFilePickerLauncher.launch("application/pdf, application/vnd.ms-excel, image/*")
            // The MIME type string allows selecting PDFs, Excel files, and images
        }) {
            Text("Select Multiple Files (PDF, Excel, Images, etc.)")
        }

        // Button to capture an image
        Button(onClick = {
            val uri = createImageFile(context)
            cameraImageUri = uri
            uri?.let {
                cameraLauncher.launch(it)
            }
        }) {
            Text("Open Camera")
        }

        // Display selected files
        Text("Selected Files:")
        selectedFiles.forEach { uri ->
            Text(uri.toString(), modifier = Modifier.padding(4.dp))
        }

        // Display captured image URI
        cameraImageUri?.let {
            Text("Camera Image URI: $it", modifier = Modifier.padding(4.dp))
        }
    }
}


