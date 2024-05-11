package com.example.project

import android.util.Log


class ParkingRepository(private val endPoint: EndPoint.Companion) {
    suspend fun getAllParkings(): List<Parking>? {
        return try {
            val response = endPoint.create().getParking()
            if (response.isSuccessful) {
                response.body() // Aucun besoin de vérifier si response.body() est null, car il sera nullable
            } else {
                null // Retourner null en cas d'échec de la requête
            }
        } catch (e: Exception) {
            null // Retourner null en cas d'exception
        }
    }

    suspend fun getParkingById(parkingId: Int): Parking? {
        return try {
            val response = endPoint.create().getParkingById(parkingId)
            if (response.isSuccessful) {
                response.body()
            } else {
                // Logguer l'erreur pour l'examen ultérieur
                Log.e("ParkingRepository", "Failed to get parking details. Response code: ${response.code()}")
                null
            }
        } catch (e: Exception) {
            // Logguer l'exception pour l'examen ultérieur
            Log.e("ParkingRepository", "Exception while getting parking details", e)
            null
        }
    }

    suspend fun getParkingByName(parkingName: String): List<Parking>? {
        return try {
            val response = endPoint.create().getParkingByName(parkingName)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }


}
