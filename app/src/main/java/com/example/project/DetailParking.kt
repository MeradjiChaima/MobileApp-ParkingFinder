package com.example.project

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp


@Composable
fun DetailParking(context: Context = LocalContext.current , parkingId: Int, parkingModal: ParkingModal, reservationModal: ReservationModal, authManager: AuthentificationManager) {
    var showServices by remember { mutableStateOf(false) }
    var showReviews by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var dateDebut by remember { mutableStateOf("") }
    var dateFin by remember { mutableStateOf("") }
    var typePlace by remember { mutableStateOf("") }
//    val emailUser =
//        authManager.getUserEmail() // Récupérer l'e-mail de l'utilisateur à partir des préférences partagées

    parkingModal.getParkingById(parkingId)
    val parking = parkingModal.parking.value


    if (parking != null) {
        parkingModal.getBusInfoByParkingID(parking.ID_parking)
        val busRequest = parkingModal.parkingBus.value
        parkingModal.getBikeInfoByParkingID(parking.ID_parking)
        val bikeRequest = parkingModal.parkingBike.value
        parkingModal.getCarInfoByParkingID(parking.ID_parking)
        val carRequest = parkingModal.parkingCar.value
        parkingModal.getServicesByParkingID(parking.ID_parking)
        val Services = parkingModal.allServices.value
        parkingModal.getReviewsByParkingID(parking.ID_parking)
        val Reviews = parkingModal.allReviews.value

        LazyColumn(modifier = Modifier.fillMaxSize()) {




           item {
               parking.displayImage(modifier = Modifier.fillMaxSize())
               Row(modifier = Modifier
                   .padding(top = 8.dp)
                   .padding(start = 5.dp)){
                   Row(

                   ) {
                       CustomIcon(Icons.Default.LocationOn) {
                           val latitude = parking!!.Coord_x
                           val longitude = parking!!.Coord_y
                           val data = Uri.parse("geo:$latitude,$longitude")
                           val intent = Intent(Intent.ACTION_VIEW, data)
                           context.startActivity(intent)
                       }


                       Text(
                           text = parking!!.Commune,
                           modifier = Modifier.padding( top = 8.dp)
                       )
                   }
                   Spacer(modifier = Modifier.width(40.dp))
                   Row {
                       Image(
                           painter = painterResource(id = R.drawable.baseline_directions_car_24),
                           contentDescription = null,
                           modifier = Modifier.size(30.dp)
                       )
                       Text(
                           text = "Total:"+parking!!.TotalPlace,
                           modifier = Modifier.padding( top = 8.dp))
                   }

               }

           }


            item {
//                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(start = 10.dp)) {
                    CustomIconCall(Icons.Default.Call) {
                        val data = Uri.parse(parking.PhoneNumber)
                        val intent = Intent(Intent.ACTION_DIAL, data)
                        context.startActivity(intent)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    parking.PhoneNumber?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding( top = 8.dp)
                        )
                    }
                }
            }


            item {
                Spacer(modifier = Modifier.height(20.dp))
                Row (modifier = Modifier.padding(start = 10.dp)){
                    if (busRequest != null) {
                        BoxWithIconAndText(painterResource(id = R.drawable.baseline_directions_car_24), busRequest.valid_bus_places ,busRequest.bus_price)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    if (carRequest != null) {
                        BoxWithIconAndText(painterResource(id = R.drawable.baseline_directions_bus_24), carRequest.valid_car_places,carRequest.car_price)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    if (bikeRequest != null) {
                        BoxWithIconAndText(painterResource(id = R.drawable.baseline_electric_bike_24), bikeRequest.valid_bike_places, bikeRequest.bike_price)
                    }
                }
            }

            item {
                Text(
                    text = parking!!.Description,
                    modifier = Modifier.padding(16.dp)
                )
            }


            //services
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp)
                        .border(2.dp, Color(0xFFDADADB), RoundedCornerShape(8.dp))
                        .clickable { showServices = !showServices }
                        .padding(10.dp)
                ) {
                    Column {
                        Text("Services", color = Color.Black, fontWeight = if (showServices) FontWeight.Bold else FontWeight.Normal)
                        Spacer(modifier = Modifier.height(10.dp))
                        if (showServices) {
                            if (Services.isNullOrEmpty()) {
                                Text("Pas de service")
                            } else {
                                Column {
                                    Services.forEach { service ->
                                        Text(service.Name, color = Color.Black)
                                        Spacer(modifier = Modifier.height(10.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(10.dp)) }

            // Reviews
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp)
                        .border(2.dp, Color(0xFFDADADB), RoundedCornerShape(8.dp))
                        .clickable { showReviews = !showReviews }
                        .padding(10.dp)
                ) {
                    Column {
                        Text("Reviews", color = Color.Black, fontWeight = if (showReviews) FontWeight.Bold else FontWeight.Normal)
                        Spacer(modifier = Modifier.height(10.dp))
                        if (showReviews) {
                            if (Reviews.isNullOrEmpty()) {
                                Text("Pas de service")
                            } else {
                                Column {
                                    Reviews.forEach { review ->
//                                        Text(service.Name, color = Color.Black)
                                        ReviewCard(review.user , review.review , review.note )
                                        Spacer(modifier = Modifier.height(10.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }













            // Book Now button
           item {
               Button(
                   onClick = { showDialog = true },
                   modifier = Modifier
                       .padding(16.dp)
                       .fillMaxWidth(),
               ) {
                   Text(text = "Book Now")
               }
           }

// Popup d'alerte pour le formulaire de réservation
            if (showDialog) {
                item {
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




            //reviews







        }
    } else {
        // Afficher une indication de chargement ou un message d'erreur si les détails du parking ne sont pas disponibles
    }
}


@Composable
fun BoxWithIconAndText(image: Painter, text: Int, text2: String) {
    Column(
        modifier = Modifier
            .border(2.dp, Color(0xFFDADADB), shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
            Text(text = ""+text, modifier = Modifier.padding(start = 8.dp))
        }
        Text(text = text2+" DZ", modifier = Modifier.padding(start = 8.dp))
    }
}

@Composable
fun ReviewCard(user: String, reviewText: String, rating: Int) {
    Box(modifier = Modifier.padding(16.dp)
        .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
        .background(Color(0xFFCFCDEA))
    ) { // Appliquer un fond de couleur ici
        Column (modifier = Modifier.padding(10.dp)){
            Text(text = user)
            Row(modifier = Modifier.fillMaxWidth()) {
                repeat(rating) {
                    Icon(Icons.Filled.Star, contentDescription = "Star", tint = Color(0xFFFED94D))
                }
                repeat(5 - rating) {
                    Icon(Icons.Filled.Star, contentDescription = "Star", tint = Color.Gray)
                }
            }
            Text(
                text = reviewText,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.clickable { /* Logique pour afficher le texte complet */ }
            )
        }
    }
}

@Composable
fun CustomIcon(imageVector: ImageVector, onClick: () -> Unit) {
    Icon(
        imageVector = imageVector,
        contentDescription = null,
        tint = Color(0xFF655AE4),
        modifier = Modifier.size(30.dp).clickable { onClick() }
    )
}


@Composable
fun CustomIconCall(imageVector: ImageVector, onClick: () -> Unit) {
    Icon(
        imageVector = imageVector,
        contentDescription = null,
        tint = Color(0xFF4CAF50),
        modifier = Modifier.size(25.dp).clickable { onClick() }
    )
}