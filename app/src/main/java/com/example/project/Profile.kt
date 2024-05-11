package com.example.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController


@Composable
fun Profile(navController: NavController, authManager: AuthentificationManager) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to your profile!")
       // Spacer(modifier = Modifier.height(16.dp) )
        // Bouton de déconnexion
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
    }
}
