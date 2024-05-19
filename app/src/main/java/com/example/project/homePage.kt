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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
    var isLoading by remember { mutableStateOf(true) } // Nouvelle variable d'état pour le chargement
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
//        isLoading = true
        if (!showDialog){
            parkingModel.getAllParkings()
            if (parkings.isEmpty()) {
                isLoading = true
                parkingModel.allParkings.value
                isLoading = false
            }else{
                isLoading = false
            }
        }else{
            parkingModel.allParkings.value
        }


    }




    Column(modifier = Modifier.fillMaxSize()) {
        Header(
            profileName = "Abbaci zoulikha"
        )
        Spacer(modifier = Modifier.height(10.dp))
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator() // Barre de progression
            }
        }else{
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
                    modifier = Modifier
                        .size(50.dp)
                        .clickable { showDialog = true }
                )
                if (showDialog) {
                    FilterPopup(onClose = { showDialog = false }, parkingModel) // Passer une fonction lambda pour fermer le dialogue
                }


            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn {
                items(parkings) { parking ->
                    ParkingItem(parking, navController)
                }
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
fun Header(profileName: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp) // Adjust elevation for desired shadow intensity
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(50.dp)
            )

            // Icons et photo de profil
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = Color(0xFFFED94D),
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = Color(0xFF655AE4),
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                // Photo de profil
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Photo de profil",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(shape = CircleShape)
                )
            }

            // Nom de profil
//            Text(
//                text = profileName,
//                fontSize = 16.sp,
//                modifier = Modifier.padding(horizontal = 8.dp)
//            )
        }
    }
}


@Composable
fun FilterPopup(onClose: () -> Unit, viewModel: ParkingModal) {
    var commune by remember { mutableStateOf("") }
    var vehicleType by remember { mutableStateOf("Car") } // Initialisation par défaut
    var maxPrice by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onClose,
        title = { Text("Search your parking:") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = commune,
                    onValueChange = { commune = it },
                    label = { Text("Commune") }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Select vehicle type:")
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = vehicleType == "Car", onClick = { vehicleType = "Car" })
                    Text("Car")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = vehicleType == "Bus", onClick = { vehicleType = "Bus" })
                    Text("Bus")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = vehicleType == "Bike", onClick = { vehicleType = "Bike" })
                    Text("Bike")
                }
                TextField(
                    value = maxPrice,
                    onValueChange = { maxPrice = it },
                    label = { Text("Max Price") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val types = listOf(vehicleType) // Création de la liste des types de véhicules
                val filters = FilterRequest(
                    commune = commune,
                    types = types,
                    maxPrice = maxPrice.toDoubleOrNull() // Conversion en Double ou null
                )
                viewModel.filterParkings(filters)
                onClose()
            }) {
                Text("Valider")
            }
        }
    )
}
