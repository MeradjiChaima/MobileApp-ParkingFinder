package com.example.project

import android.util.Log
import retrofit2.Response


class ReservationRepository(private val endPoint: EndPoint) {
    suspend fun addReservation(reservationRequest: ReservationRequest): Response<ReservationRequest> {
        return endPoint.addReservation(reservationRequest)
    }
    suspend fun getReservationByEmail(EmailUser: String): Reservation? {
        return try {
            val response = endPoint.getReservationByEmail(EmailUser)
            if (response.isSuccessful) {
                response.body()
            } else {
                // Logguer l'erreur pour l'examen ultérieur
                Log.e("ParkingRepository", "Échec de la récupération des détails de réservation. Code de réponse : ${response.code()}")
                null
            }
        } catch (e: Exception) {
            // Logguer l'exception pour l'examen ultérieur
            Log.e("ParkingRepository", "Exception lors de la récupération des détails de réservation", e)
            null
        }
    }

}