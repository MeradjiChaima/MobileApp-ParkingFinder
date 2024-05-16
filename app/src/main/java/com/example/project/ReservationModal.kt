package com.example.project

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response


class ReservationModal(private val reservationRepository: ReservationRepository) : ViewModel() {
    var isReservationSuccessful = mutableStateOf(false)
        private set

    fun addReservation(
        reservation: ReservationRequest,
        param: (Any) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response: Response<ReservationRequest> = reservationRepository.addReservation(reservation)
                if (response.isSuccessful) {
                    param(true) // Réservation réussie
                } else {
                    param(false) // Échec de la réservation
                }
            } catch (e: Exception) {
                param(e) // Gestion de l'erreur
            }
        }
    }

    fun getReservationByEmail(email: String, param: (Reservation?) -> Unit) {
        viewModelScope.launch {
            try {
                val reservation: Reservation? = reservationRepository.getReservationByEmail(email)
                param(reservation)
            } catch (e: Exception) {
                param(null)
            }
        }
    }


}