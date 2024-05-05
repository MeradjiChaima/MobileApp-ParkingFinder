package com.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun DetailParking(parkingId: Int, parkingModal: ParkingModal) {
    parkingModal.getParkingById(parkingId)
    val parking = parkingModal.parking.value
    if (parking != null) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Big parking image (Placeholder image)
            parking.displayImage()

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
                        painter = painterResource(id = R.drawable.blackcar),
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
                        painter = painterResource(id = R.drawable.grrencar),
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
                onClick = { /* Handle booking button click */ },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            ) {
                Text(text = "Book Now")
            }
        }
    } else {
        // Afficher une indication de chargement ou un message d'erreur si les détails du parking ne sont pas disponibles
    }
}
