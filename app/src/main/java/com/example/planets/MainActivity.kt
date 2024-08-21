package com.example.planets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.planets.model.getPlanets
import com.example.planets.screen.DetailsView
import com.example.planets.screen.HomePage
import com.example.planets.screen.Screen
import com.example.planets.ui.theme.PlanetsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlanetsTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val planets = getPlanets()
                var selectedPlanetInfo = remember {
                    mutableStateOf(planets.first())
                }
                NavHost(navController = navController, startDestination = Screen.Home.route) {
                    composable(route = Screen.Home.route) {
                        HomePage(isVisible = true) { planetInfo ->
                            selectedPlanetInfo.value = planetInfo
                            navController.navigate(Screen.Details.route)
                        }
                    }
                    composable(route = Screen.Details.route) {
                        DetailsView(planetInfo = selectedPlanetInfo.value, isVisible = true) {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}
