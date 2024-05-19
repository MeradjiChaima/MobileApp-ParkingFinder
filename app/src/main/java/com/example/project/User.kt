package com.example.project

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class User(
val ID_utilisateur: Int,
val Username: String,
val Adresse_email: String,
val Mot_de_passe: String,
val Compte_Gmail: String?,
val Photo: String? ,
val PhoneNumber : String
){
    @Composable
    fun displayImage(modifier: Modifier = Modifier) {
        AsyncImage(
            model = Constants.BASE_URL +"users/"+Photo ,
            contentDescription = null , // Provide a description if needed
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }

}

