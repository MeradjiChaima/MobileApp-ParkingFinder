package com.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun DetailParking(parkingId: Int, parkingModal: ParkingModal, reservationModal: ReservationModal, authManager: AuthentificationManager) {

    var showDialog by remember { mutableStateOf(false) }
    var dateDebut by remember { mutableStateOf("") }
    var dateFin by remember { mutableStateOf("") }
    var typePlace by remember { mutableStateOf("") }
    val emailUser =
        authManager.getUserEmail() // Récupérer l'e-mail de l'utilisateur à partir des préférences partagées

    parkingModal.getParkingById(parkingId)
    val parking = parkingModal.parking.value

    if (parking != null) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Big parking image (Placeholder image)
            parking.displayImage(modifier = Modifier.fillMaxWidth())

            // Location
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color(0xFF655AE4),
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    text = parking!!.Commune,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            // Total and available spaces
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.carnoire),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                    Text(
                        text = parking!!.Num_reserves.toString(),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.cargreen),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                    Text(
                        text = parking!!.Num_valides.toString(),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            // Description
            Text(
                text = parking!!.Description,
                modifier = Modifier.padding(16.dp)
            )

            // Book Now button
            Button(
                onClick = { showDialog = true },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            ) {
                Text(text = "Book Now")
            }

// Popup d'alerte pour le formulaire de réservation
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text(text = "Booking a place") },
                    confirmButton = {
                        Button(
                            onClick = {
                                // Ajouter la réservation lorsque le bouton "Réserver" est cliqué
                                val reservation = ReservationRequest(

                                    ID_parking = parking.ID_parking,
                                    Date_debut = dateDebut,
                                    Date_fin = dateFin,
                                    Type_place = typePlace,
                                    EmailUser = "koko@gmal.com"
                                )

                                reservationModal.addReservation(reservation) { result ->
                                    when (result) {
                                        is Boolean -> {
                                            if (result) {

                                                showDialog = false // Fermer la boîte de dialogue après avoir ajouté la réservation
                                            } else {
                                                // Échec de la réservation
//                                                showDialog = false // Fermer la boîte de dialogue en cas d'erreur
//                                                println("Error adding reservation")
                                            }
                                        }
                                        is Exception -> {
                                            // Gérer l'erreur
                                            showDialog = false // Fermer la boîte de dialogue en cas d'erreur
                                            println("Error adding reservation: ${result.message}")
                                        }
                                        else -> {
                                            // Cas non prévu
                                            showDialog = false // Fermer la boîte de dialogue en cas d'erreur
                                            println("Unexpected result type")
                                        }
                                    }
                                }





                            },
                        ) {
                            Text(text = "Réserver")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { showDialog = false },
                        ) {
                            Text(text = "Annuler")
                        }
                    },
                    text = {
                        // Contenu du popup
                        Column {
                            // Input Date de début
                            TextField(
                                value = dateDebut,
                                onValueChange = { dateDebut = it },
                                label = { Text("Date de début") },
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            // Input Date de fin
                            TextField(
                                value = dateFin,
                                onValueChange = { dateFin = it },
                                label = { Text("Date de fin") },
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            // Input Type de place
                            TextField(
                                value = typePlace,
                                onValueChange = { typePlace = it },
                                label = { Text("Type de place") },
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    }
                )
            }
        }
    } else {
        // Afficher une indication de chargement ou un message d'erreur si les détails du parking ne sont pas disponibles
    }
}


