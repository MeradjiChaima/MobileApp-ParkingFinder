package com.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun Profile(userModal: UserModal, authManager:AuthentificationManager, navController: NavController) {
    val loggedInUserId = authManager.getUserId()

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
            Image(
                painter = painterResource(id = R.drawable.profile), // Replace 'image' with your actual drawable resource
                contentDescription = "Photo de profil",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
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
                        // Éviter d'ajouter la destination à la pile de retour lorsque vous revenez à l'écran de connexion
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


