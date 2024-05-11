package com.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavController, authManager: AuthentificationManager, userModal: UserModal) {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf("") }

    if (authManager.isLoggedIn()) {
        navController.navigate(Destination.Home.route)
        return
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.headerlogin),
                contentDescription = "Filter",
                modifier = Modifier.height(200.dp) // Ajuster la hauteur de l'image en haut de la page
                    .width(300.dp)
            )
        }
        Spacer(modifier = Modifier.height(60.dp)) // Ajouter un espace entre l'image et le texte

        Text(
            text = "Login Now",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(20.dp)) // Ajouter un espace entre le bouton de connexion et l'image de pied de page

// Afficher le message d'erreur s'il y en a un
        if (errorMessage.value.isNotEmpty()) {
            Text(
                text = errorMessage.value,
                color = Color.Red,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp)) // Ajouter un espace entre le bouton de connexion et l'image de pied de page

        OutlinedTextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp , start =50.dp , end =50.dp )
        )

        OutlinedTextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 10.dp , start =50.dp , end =50.dp )
        )

        Button(
            onClick = {
                // Appeler la méthode loginUser lorsque le bouton est cliqué
                userModal.loginUser(
                    email = emailState.value,
                    password = passwordState.value
                ) { success ->
                    if (success) {
                        authManager.saveCredentials(emailState.value, passwordState.value)
                        navController.navigate(Destination.Home.route)
                    } else {
                        errorMessage.value = "Email or password is incorrect. Please try again."
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp , start =50.dp , end =50.dp )
        ) {
            Text(text = "Login")
        }

        TextButton(
            onClick = {
                navController.navigate(Destination.Register.route)

            }
        ) {
            Text(text = "You don't have an account? Register now")
        }



        Spacer(modifier = Modifier.height(20.dp))
//        Box(
//            contentAlignment = Alignment.CenterStart,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.footerpage),
//                contentDescription = "Filter",
//                modifier = Modifier
//                    .height(200.dp) // Ajuster la hauteur de l'image en haut de la page
//                    .width(300.dp)
//
//            )
//        }
    }


}

