package com.example.project

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.project.Constants.BASE_URL

class Parking(
    val ID_parking: Int,
    val Nom: String,
    val Photo: String,
    val Commune: String,
    val Description: String,
    val TotalPlace: Int,
    val Num_valides: Int,
    val Prix_place: Double,
    val Coord_x: Double,
    val Coord_y: Double,
    val PhoneNumber: String ?
){
    @Composable
    fun displayImage(modifier: Modifier = Modifier) {
        AsyncImage(
            model = BASE_URL+"parkings/"+Photo ,
            contentDescription = null , // Provide a description if needed
            modifier = modifier.fillMaxWidth()
        )
    }
    @Composable
    fun displayImage2() {
        AsyncImage(
            model = BASE_URL+"parkings/"+Photo ,
            contentDescription = null , // Provide a description if needed
            modifier = Modifier.width(140.dp)
                .height((120.dp))
        )
    }

}
