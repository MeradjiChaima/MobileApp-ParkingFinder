package com.example.project

data class Parking(
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
)
