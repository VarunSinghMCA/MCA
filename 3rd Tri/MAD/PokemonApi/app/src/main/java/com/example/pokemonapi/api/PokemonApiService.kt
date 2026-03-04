package com.example.pokemonapi.api

import com.example.pokemonapi.data.PokemonDetail
import com.example.pokemonapi.data.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 5000,
        @Query("offset") offset: Int = 0
    ): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(@Path("name") name: String): PokemonDetail
}

