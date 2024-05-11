package com.example.project

import retrofit2.Response


class ReservationRepository(private val endPoint: EndPoint) {
    suspend fun addReservation(reservationRequest: ReservationRequest): Response<ReservationRequest> {
        return endPoint.addReservation(reservationRequest)
    }
}