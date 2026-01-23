package com.example.navigation_trial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Routes.SCREEN_A
            ) {
                composable(Routes.SCREEN_A) {
                    ScreenA(navController)
                }

                composable(Routes.SCREEN_B + "/{name}") { backStackEntry ->
                    val name = backStackEntry.arguments?.getString("name")
                    ScreenB(name = name ?: "No Argument")
                }
            }
        }
    }
}
