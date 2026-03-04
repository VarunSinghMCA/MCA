package com.example.pokemonapi.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapi.api.RetrofitClient
import com.example.pokemonapi.data.PokemonDetail
import kotlinx.coroutines.launch
import android.util.Log


class PokemonViewModel : ViewModel() {

    private val _pokemonState = mutableStateOf(PokemonState())
    val pokemonState: State<PokemonState> = _pokemonState

    init {
        fetchPokemon()
    }

    private fun fetchPokemon() {
        viewModelScope.launch {
            try {
                _pokemonState.value = _pokemonState.value.copy(loading = true, error = null)
                val response = RetrofitClient.apiService.getPokemonList()
                val details = response.results.mapNotNull { item ->
                    try { RetrofitClient.apiService.getPokemonDetail(item.name) } catch (e: Exception) { Log.e("PokemonViewModel", "Error fetching details for ${item.name}: ${e.message}") ; null }
                }
                _pokemonState.value = PokemonState(loading = false, list = details)
            } catch (e: Exception) {
                _pokemonState.value = PokemonState(loading = false, error = "Error: ${e.message}")
            }
        }
    }

    data class PokemonState(
        val loading: Boolean = true,
        val list: List<PokemonDetail> = emptyList(),
        val error: String? = null
    )
}
