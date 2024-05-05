package com.example.project

import androidx.compose.runtime.Composable
import coil.compose.AsyncImage

class Parking(
    val ID_parking: Int,
    val Nom: String,
    val Photo: String,
    val Commune: String,
    val Description: String,
    val Num_reserves: Int,
    val Num_valides: Int,
    val Prix_place: Double,
    val Coord_x: Double,
    val Coord_y: Double
){
    @Composable
    fun displayImage() {
        AsyncImage(
            model = "https://78f7-129-45-24-23.ngrok-free.app/parkings/"+Photo ,
            contentDescription = null // Provide a description if needed
        )
    }

}
