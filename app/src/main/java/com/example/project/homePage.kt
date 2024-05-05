package com.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun homePage(parkingModel: ParkingModal, navController: NavController) {
    val profilePicture = painterResource(id = R.drawable.profile) // Assuming the image is in drawable

    // Initialize parkings with an empty list as default
    val parkings = parkingModel.allParkings.value

    // LaunchedEffect to fetch parkings when the composable is initially launched
    LaunchedEffect(Unit) {
        if (parkings.isEmpty()) {
            // If the list is empty, fetch parkings
            parkingModel.allParkings.value
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Header(
            profilePicture = profilePicture,
            profileName = "Abbaci zoulikha"
        )

        LazyColumn {
            items(parkings) { parking ->
                ParkingItem(parking, navController)
            }
        }
    }
}


@Composable
fun ParkingItem(parking: Parking,navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
//        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .background(color = Color(0xFFEFEEF6))
                .padding(8.dp)
                .fillMaxWidth()
                .clickable {
                    navController.navigate(Destination.DetailParking.createRoute(parking.ID_parking))
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
                parking.displayImage()
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = parking.Nom,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    fontSize = 16.sp
                )
                Text(text = parking.Commune, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.grrencar),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(text = "${parking.Num_valides} available out of ${parking.Num_reserves}")
                }
            }
        }
    }
}

@Composable
fun Header(profilePicture: Painter, profileName: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp) // Adjust elevation for desired shadow intensity
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically,
        ) {
            // Photo de profil
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = profilePicture,
                    contentDescription = "Photo de profil",
                    modifier = Modifier.size(40.dp).clip(shape = CircleShape)
                )
                // Nom de profil
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    onTextLayout = {},
                    text = profileName,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            // Icône de notification
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = Color(0xFF655AE4),
                modifier = Modifier.size(30.dp)
            )
        }
    }
}


