package com.example.project

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface EndPoint {

    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun create(): EndPoint {
            return retrofit.create(EndPoint::class.java)
        }




    }
    @GET("parkings")
    suspend fun getParking(): Response<List<Parking>>
    @GET("parking/{parkingId}")
    suspend fun getParkingById(@Path("parkingId") parkingId: Int): Response<Parking>
    @POST("login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<User>
    @POST("register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<User>

    @GET("user/id")
    suspend fun getUserIdByEmail(@Query("email") email: String): Response<User>

    @GET("userId")
    suspend fun getUserById(@Query("id") id: Int ): Response<User>

    @GET("parkingSearch")
    suspend fun getParkingByKeyword(@Query("keyword") keyword: String): Response<List<Parking>>
    @POST("makeReservation")
    suspend fun addReservation(@Body reservationRequest:ReservationRequest): Response<ReservationRequest>
    @GET("reservationsByEmail/{EmailUser}")
    suspend fun getReservationByEmail(@Path("EmailUser") EmailUser: String): Response<Reservation>

    @GET("busInfo/{idParking}")
    suspend fun getBusInfoByParkingID(@Path("idParking") idParking: Int): Response<RequestBus>
    @GET("bikeInfo/{idParking}")
    suspend fun getBikeInfoByParkingID(@Path("idParking") idParking: Int): Response<RequestBike>
    @GET("carInfo/{idParking}")
    suspend fun getCarInfoByParkingID(@Path("idParking") idParking: Int): Response<RequestCar>
    @GET("services/{idParking}")
    suspend fun getServicesByParkingID(@Path("idParking") idParking: Int): Response<List<Service>>

    @GET("parkingReviews/{idParking}")
    suspend fun getReviewsByParkingID(@Path("idParking") idParking: Int): Response<List<Review>>


}
