package com.example.project

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.io.File


@Composable
fun Profile(userModal: UserModal, authManager:AuthentificationManager, navController: NavController) {
    val loggedInUserId = authManager.getUserId()
    val context = LocalContext.current
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    val selectImageLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            if (it.scheme == ContentResolver.SCHEME_CONTENT) {
                // Content URI, handle differently
                val contentResolver = context.contentResolver
                val inputStream = contentResolver.openInputStream(uri)
                inputStream?.let { inputStream ->
                    val tempFile = File.createTempFile("temp", null, context.cacheDir)
                    tempFile.outputStream().use { outputStream ->
                        inputStream.copyTo(outputStream)
                    }
                    imageUri.value = Uri.fromFile(tempFile)
                }
            } else {
                // File URI, proceed as usual
                imageUri.value = it
            }
        }
    }

    LaunchedEffect(imageUri.value) {
        imageUri.value?.let { uri ->
            val file = File(uri.path)
            userModal.updateProfilePicture(file, loggedInUserId)
        }
    }

    LaunchedEffect(loggedInUserId) {
         userModal.getUserById(loggedInUserId) // Assurez-vous que l'ID est correct ici
    }

    userModal.user?.let { usere ->
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Page profile",
                fontSize = 32.sp,
                color = Color(0xFFFED94D),
                modifier = Modifier.padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            usere.displayImage()
            ChangeProfilePictureButton(onSelectImage = {
                selectImageLauncher.launch("image/*") // Ouvre le sélecteur d'image pour tous les types d'images
            })

            Spacer(modifier = Modifier.height(32.dp)) // Space between image and card
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFC6C6C6)),
                modifier = Modifier.padding(6.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "UserName :",
                        color = Color(0xFFFED94D),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Text(
                        text = usere?.Username ?: "N/A",
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Adresse Email :",
                        color = Color(0xFFFED94D),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Text(
                        text = usere?.Adresse_email ?: "N/A",
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Mot de passe :",
                        color = Color(0xFFFED94D),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Text(
                        text = usere?.Mot_de_passe ?: "N/A",
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Compte Gmail :",
                        color = Color(0xFFFED94D),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    if (usere != null) {
                        usere.Compte_Gmail?.let {
                            Text(
                                text = it,
                                fontSize = 16.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(horizontal = 20.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Button(
                onClick = {
                    authManager.logout()
                    navController.navigate(Destination.Login.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        // Permettre de revenir à l'écran de connexion
                        restoreState = true
                    }
                    navController.navigate(Destination.Login.route)
                }
            ) {
                Text(text = "Logout")
            }


            Button(
                onClick = { /* Action pour voir les réservations */ },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFFED94D))
            ) {
                Text("Voir mes réservations", color = Color.Black)
            }
        }
    }
}

@Composable
fun ChangeProfilePictureButton(onSelectImage: () -> Unit) {
    IconButton(onClick = onSelectImage) {
        Icon(
            imageVector = Icons.Default.Create,
            contentDescription = null,
        )
    }
}

fun Uri.toFile(context: Context): File {
    return File(getPath()).apply {
        parentFile.mkdirs()
    }
}
