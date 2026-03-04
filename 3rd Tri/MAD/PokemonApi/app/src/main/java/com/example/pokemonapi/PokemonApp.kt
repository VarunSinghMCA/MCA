package com.example.pokemonapi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pokemonapi.navigation.Screen
import com.example.pokemonapi.ui.PokemonDetailScreen
import com.example.pokemonapi.viewmodel.PokemonViewModel

@Composable
fun PokemonApp(navController: NavHostController) {
    val pokemonViewModel: PokemonViewModel = viewModel()
    val viewState by pokemonViewModel.pokemonState

    NavHost(navController = navController, startDestination = Screen.PokemonScreen.route) {

        composable(route = Screen.PokemonScreen.route) {
            PokemonScreen(
                viewState = viewState,
                navigateToDetail = { name ->
                    navController.navigate(Screen.DetailScreen.createRoute(name))
                }
            )
        }

        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            PokemonDetailScreen(pokemonName = name, navController = navController)
        }
    }
}
