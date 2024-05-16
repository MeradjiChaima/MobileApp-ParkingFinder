package com.example.project

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class ParkingModal(private val parkingRepository: ParkingRepository) : ViewModel() {
    var allParkings = mutableStateOf<List<Parking>>(emptyList())
    var allServices = mutableStateOf<List<Service>>(emptyList())
    var allReviews = mutableStateOf<List<Review>>(emptyList())
    var parking = mutableStateOf<Parking?>(null) // Définir parking comme un état mutable
    var parkingBus = mutableStateOf<RequestBus?>(null)
    var parkingBike = mutableStateOf<RequestBike?>(null)
    var parkingCar = mutableStateOf<RequestCar?>(null)

    fun getAllParkings() {
        viewModelScope.launch {
            val parkings = parkingRepository.getAllParkings()
            if (parkings != null) {
                allParkings.value = parkings
            }
        }
    }

    fun getParkingById(parkingId: Int) {
        viewModelScope.launch {
            try {
                val response = parkingRepository.getParkingById(parkingId)
                if (response != null) {
                    parking.value =
                        response // Mettre à jour l'état avec les détails du parking récupéré
                } else {
                    // Gérer le cas où response est null ou la requête a échoué
                }
            } catch (e: Exception) {
                // Gérer les exceptions
            }
        }
    }

    // Factory pour créer une instance de ParkingModel
    class Factory(private val parkingRepository: ParkingRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ParkingModal(parkingRepository) as T
        }
    }

    fun searchParkingByName(parkingName: String) {
        viewModelScope.launch {
            val parkings = parkingRepository.getParkingByName(parkingName)
            if (parkings != null) {
                allParkings.value = parkings
            }
        }
    }

    fun getBusInfoByParkingID(parkingId: Int) {
        viewModelScope.launch {
            try {
                val response = parkingRepository.getBusInfoByParkingID(parkingId)
                if (response != null) {
                    parkingBus.value =
                        response // Mettre à jour l'état avec les détails du parking récupéré
                } else {
                    // Gérer le cas où response est null ou la requête a échoué
                }
            } catch (e: Exception) {
                // Gérer les exceptions
            }
        }
    }

    fun getBikeInfoByParkingID(parkingId: Int) {
        viewModelScope.launch {
            try {
                val response = parkingRepository.getBikeInfoByParkingID(parkingId)
                if (response != null) {
                    parkingBike.value =
                        response // Mettre à jour l'état avec les détails du parking récupéré
                } else {
                    // Gérer le cas où response est null ou la requête a échoué
                }
            } catch (e: Exception) {
                // Gérer les exceptions
            }
        }
    }

    fun getCarInfoByParkingID(idParking: Int) {
        viewModelScope.launch {
            try {
                val response = parkingRepository.getCarInfoByParkingID(idParking)
                if (response != null) {
                    parkingCar.value =
                        response // Mettre à jour l'état avec les détails du parking récupéré
                } else {
                    // Gérer le cas où response est null ou la requête a échoué
                }
            } catch (e: Exception) {
                // Gérer les exceptions
            }
        }
    }

    fun getServicesByParkingID(idParking: Int) {
        viewModelScope.launch {
            try {
                val response = parkingRepository.getServicesByParkingID(idParking)
                if (response != null) {
                    allServices.value = response // Mettre à jour l'état avec les détails du parking récupéré
                } else {
                }
            } catch (e: Exception) {
            }
        }
    }

    fun getReviewsByParkingID(idParking: Int) {
        viewModelScope.launch {
            try {
                val response = parkingRepository.getReviewsByParkingID(idParking)
                if (response != null) {
                    allReviews.value = response // Mettre à jour l'état avec les détails du parking récupéré
                } else {
                }
            } catch (e: Exception) {

            }
        }
    }






}
