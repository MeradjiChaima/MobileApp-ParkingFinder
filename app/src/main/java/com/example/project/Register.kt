package com.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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

@Composable
fun Register(navController: NavController, authManager: AuthentificationManager, userModal: UserModal) {
    val userNameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf("") }
//    val isLoggedIn = userModal.isLoggedIn.value
    if (authManager.isLoggedIn()) {
        navController.navigate(Destination.Home.route)
        return
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.headerlogin),
                contentDescription = "Filter",
                modifier = Modifier.height(200.dp)
                    .width(300.dp)
            )
        }
        Spacer(modifier = Modifier.height(40.dp)) // Ajouter un espace entre l'image et le texte

        Text(
            text = "Register Now",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(10.dp)) // Ajouter un espace entre le bouton de connexion et l'image de pied de page


        if (errorMessage.value.isNotEmpty()) {
            Text(
                text = errorMessage.value,
                color = Color.Red,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = userNameState.value,
            onValueChange = { userNameState.value = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp , start =50.dp , end =50.dp )
        )
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

                userModal.registerUser(
                    username=  userNameState.value ,
                    email = emailState.value,
                    password = passwordState.value
                ) { success ->
                    if (success) {
                        navController.navigate(Destination.Home.route)
                    } else {
                        errorMessage.value = "Email already existe!, try to login "
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp , start =50.dp , end =50.dp )
        ) {
            Text(text = "Register")
        }

        TextButton(
            onClick = {
                navController.navigate(Destination.Login.route)

            }
        ) {
            Text(text = "You have an account? Login now")
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