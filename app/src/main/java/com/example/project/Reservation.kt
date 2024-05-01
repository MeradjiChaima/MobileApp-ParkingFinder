package com.example.project

data class Reservation(
    val ID_reservation: Int,
    val ID_utilisateur: Int,
    val ID_parking: Int,
    val Num_place: String,
    val Date_debut: String?, // Ou vous pouvez utiliser le type de date approprié pour Kotlin
    val Date_fin: String?, // Ou vous pouvez utiliser le type de date approprié pour Kotlin
    val Code_QR: String?,
    val Type_place: String?
)
