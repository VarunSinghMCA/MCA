package com.example.pokemonapi.data

import com.google.gson.annotations.SerializedName

data class PokemonResponse(val results: List<PokemonItem>)

data class PokemonItem(val name: String, val url: String)

data class PokemonDetail(val id: Int, val name: String, val sprites: Sprites)

data class Sprites(@SerializedName("front_default") val frontDefault: String?)
