//package com.example.project
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.room.util.TableInfo
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.material3.Button
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//
//@Composable
//fun MyReservations(reservationModal: ReservationModal, authManager: AuthentificationManager) {
//    // Récupération de l'email de l'utilisateur
//    val userEmail = authManager.getUserEmail()
//
//    // Appel de la fonction getReservationByEmail avec l'email de l'utilisateur
//    if (userEmail != null) {
//        reservationModal.getReservationByEmail(userEmail) { reservation ->
//            // Vérification si une réservation a été trouvée
//            if (reservation!= null) {
//                // Affichage des détails de la réservation
//              Column{  Text(text = "Réservation trouvée : ${reservation.id}")}
//                // Yous pouvez ajouter plus de détails sur la réservation ici
//            } else {
//                // Message en cas d'absence de réservation
//                Text(text = "Aucune réservation trouvée pour cet utilisateur.")
//            }
//        }
//    }
//}
