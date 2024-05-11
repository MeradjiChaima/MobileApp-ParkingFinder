package com.example.project

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class ParkingModal(private val parkingRepository: ParkingRepository) : ViewModel() {
    var allParkings = mutableStateOf<List<Parking>>(emptyList())
    var parking = mutableStateOf<Parking?>(null) // Définir parking comme un état mutable

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
                    parking.value = response // Mettre à jour l'état avec les détails du parking récupéré
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


}
