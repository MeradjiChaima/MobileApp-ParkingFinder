package com.example.project

open class Destination(val route:String) {
    object Home: Destination("home")
    object Main: Destination("main")
    object Register: Destination("register")
    object Login: Destination("login")
    object Map: Destination("map")
    object SaveParking: Destination("saveParking")
    object Profile: Destination("profile")
    object DetailParking: Destination("detailParking")
    fun createRoute(parkingId:Int) = "detailParking/$parkingId"
}
