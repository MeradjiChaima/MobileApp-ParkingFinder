package com.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project.ui.theme.ProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val parkingModel: ParkingModal by viewModels {
            ParkingModal.Factory((applicationContext as MyApplication).parkingRepository)
        }
        parkingModel.getAllParkings() // Appel à la méthode getAllParkings() pour charger les parkings
        val userRepository = UserRepository(EndPoint.create())
        val userModal = UserModal(userRepository = userRepository)


        val reservationRepository = ReservationRepository(EndPoint.create())
        val reservationModal = ReservationModal(reservationRepository = reservationRepository)

        setContent {
            ProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val authManager = AuthentificationManager(context = applicationContext)

                    NavigationExample(navController = navController, authManager = authManager, parkingModel = parkingModel, userModal = userModal, reservationModal = reservationModal) // Passage de reservationModal à NavigationExample
                }
            }
        }
    }
}



@Composable
fun NavigationExample(navController: NavHostController, authManager: AuthentificationManager, parkingModel: ParkingModal, userModal: UserModal, reservationModal: ReservationModal) {

    NavHost(navController = navController, startDestination = Destination.Login.route) {
        composable(Destination.Login.route) {
            Login(navController = navController, authManager = authManager, userModal = userModal)
        }
        composable(Destination.Register.route) {
            Register(navController = navController, authManager = authManager, userModal = userModal)
        }
        composable(Destination.Home.route) {
            ScaffoldWithBottomBar(navController = navController, currentRoute = Destination.Home.route) {
                homePage(parkingModel = parkingModel, navController = navController)
            }
        }
        composable(Destination.Map.route) {
            ScaffoldWithBottomBar(navController = navController, currentRoute = Destination.Map.route) {
                Map(navController = navController, authManager = authManager)
            }
        }
        composable(Destination.Profile.route) {
            ScaffoldWithBottomBar(navController = navController, currentRoute = Destination.Profile.route) {
                Profile(navController = navController, authManager = authManager)
            }
        }
        composable(Destination.DetailParking.route + "/{parkingId}") { // Ajout de l'argument parkingId à la route
            val parkingId = it.arguments?.getString("parkingId")?.toIntOrNull() // Utilisation de toIntOrNull() pour gérer les valeurs null
            if (parkingId != null) {
                ScaffoldWithBottomBar(navController = navController, currentRoute = Destination.DetailParking.route) {
                    DetailParking(parkingId = parkingId, parkingModal = parkingModel ,reservationModal = reservationModal ,authManager = authManager)
                }
            }
        }
    }
}


@Composable
fun ScaffoldWithBottomBar(navController: NavHostController, currentRoute: String, content: @Composable () -> Unit) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentRoute == Destination.Home.route,
                    onClick = { navController.navigate(Destination.Home.route) },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = null,
                            tint = if (currentRoute == Destination.Home.route) Color(0xFF655AE4) else Color.Gray,
                            modifier = Modifier.size(if (currentRoute == Destination.Home.route) 40.dp else 24.dp)
                        )
                    }
                )
                NavigationBarItem(
                    selected = currentRoute == Destination.Main.route,
                    onClick = { navController.navigate(Destination.Map.route) },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = if (currentRoute == Destination.Map.route) Color(0xFF655AE4) else Color.Gray,
                            modifier = Modifier.size(if (currentRoute == Destination.Map.route) 40.dp else 24.dp)
                        )
                    }
                )
                NavigationBarItem(
                    selected = currentRoute == Destination.Main.route,
                    onClick = { navController.navigate(Destination.Profile.route) },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = if (currentRoute == Destination.Profile.route) Color(0xFF655AE4) else Color.Gray,
                            modifier = Modifier.size(if (currentRoute == Destination.Profile.route) 40.dp else 24.dp)
                        )
                    }
                )
                NavigationBarItem(
                    selected = currentRoute == Destination.Main.route,
                    onClick = { navController.navigate(Destination.SaveParking.route) },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = null,
                            tint = if (currentRoute == Destination.SaveParking.route) Color(0xFF655AE4) else Color.Gray,
                            modifier = Modifier.size(if (currentRoute == Destination.SaveParking.route) 40.dp else 24.dp)
                        )
                    }
                )
            }
        }
    ) {
        content()
    }
}

