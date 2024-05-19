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
            val response = endPoint.create().getParkingByKeyword(parkingName)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
    suspend fun getBusInfoByParkingID(parkingId:Int): RequestBus?{
        return try {
            val response = endPoint.create().getBusInfoByParkingID(parkingId)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }


    suspend fun getBikeInfoByParkingID(parkingId:Int): RequestBike?{
        return try {
            val response = endPoint.create().getBikeInfoByParkingID(parkingId)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }


    suspend fun getCarInfoByParkingID(parkingId:Int): RequestCar?{
        return try {
            val response = endPoint.create().getCarInfoByParkingID(parkingId)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }




    suspend fun getServicesByParkingID(Idparking:Int): List<Service>? {
        return try {
            val response = endPoint.create().getServicesByParkingID(Idparking)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getReviewsByParkingID(Idparking:Int): List<Review>? {
        return try {
            val response = endPoint.create().getReviewsByParkingID(Idparking)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun filterParkings(filters: FilterRequest): List<Parking>? {
        return try {
            val response = endPoint.create().filterParkings(filters)
            if (response.isSuccessful) {
                response.body() // Retourne les parkings filtrés si la requête est réussie
            } else {
                null // Retourne null si la requête a échoué
            }
        } catch (e: Exception) {
            null // Retourne null en cas d'exception
        }
    }



}
