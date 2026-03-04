package com.example.pokemonapi.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val URL = "https://pokeapi.co/api/v2/"

    val apiService: PokemonApiService by lazy {
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApiService::class.java)
    }
}

