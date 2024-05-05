package com.example.project

import android.app.Application


class MyApplication: Application() {

    val parkingRepository by lazy { ParkingRepository(endPoint = EndPoint) }
}