package com.example.project

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    fun displayImage(modifier: Modifier = Modifier) {
        AsyncImage(
            model = "https://9dbb-129-45-28-171.ngrok-free.app/parkings/"+Photo ,
            contentDescription = null , // Provide a description if needed
            modifier = modifier.fillMaxWidth()
        )
    }
    @Composable
    fun displayImage2() {
        AsyncImage(
            model = "https://9dbb-129-45-28-171.ngrok-free.app/parkings/"+Photo ,
            contentDescription = null , // Provide a description if needed
            modifier = Modifier.width(140.dp)
                .height((120.dp))
        )
    }

}
