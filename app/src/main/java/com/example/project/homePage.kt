package com.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
    val profilePicture = painterResource(id = R.drawable.profile)
    var searchQuery by remember { mutableStateOf("") }
    val parkings = parkingModel.allParkings.value

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
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Search input field
            OutlinedTextField(
                value = searchQuery, // Utilisez la variable d'état pour la valeur
                onValueChange = { newValue -> // Mise à jour de la variable d'état lorsque l'utilisateur saisit du texte
                    searchQuery = newValue
                    parkingModel.searchParkingByName(newValue) // Appel de la méthode de recherche avec la nouvelle valeur
                },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Search...") } // Placeholder text for the input field
            )

            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.baseline_filter_list_24),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )

        }
        Spacer(modifier = Modifier.height(10.dp))
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
    ) {
        Row(
            modifier = Modifier
                .background(color = Color(0xFFEFEEF6))
                .padding(8.dp)
                .fillMaxSize()
                .clickable {
                    navController.navigate(Destination.DetailParking.createRoute(parking.ID_parking))
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            parking.displayImage2()
            Spacer(modifier = Modifier.width(8.dp))
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
                Spacer(modifier = Modifier.height(4.dp))

                Row(){
                   Icon(
                       imageVector = Icons.Default.LocationOn,
                       contentDescription = null,
                       tint =  Color(0xFFFED94D)
                   )
                   Text(text = parking.Commune, fontSize = 14.sp)
               }


                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Row(){
                        Image(
                            painter = painterResource(id = R.drawable.baseline_directions_car_24),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                        Text(text = "${parking.TotalPlace}%")
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_directions_car_filled_24),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                        Text(text = "${parking.Num_valides}%")
                    }
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

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )


            // Photo de profil
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = Color(0xFFFED94D),
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))

                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = null,
//                    tint = Color(0xFF655AE4),
                    modifier = Modifier.size(25.dp)
                )
                Image(
                    painter = profilePicture,
                    contentDescription = "Photo de profil",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(shape = CircleShape)
                )
                // Nom de profil

            }

            // Icône de notification

        }
    }
}


