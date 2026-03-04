package com.example.pokemonapi.navigation

sealed class Screen(val route: String) {
    object PokemonScreen : Screen("pokemon_list")
    object DetailScreen : Screen("pokemon_detail/{name}") {
        fun createRoute(name: String) = "pokemon_detail/$name"
    }
}
