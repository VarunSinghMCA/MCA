package com.example.pokemonapi

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.pokemonapi.data.PokemonDetail
import com.example.pokemonapi.viewmodel.PokemonViewModel

@Composable
fun PokemonScreen(
    viewState: PokemonViewModel.PokemonState,
    navigateToDetail: (String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            viewState.loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            viewState.error != null -> Text("ERROR OCCURRED", modifier = Modifier.align(Alignment.Center))
            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(viewState.list) { pokemon ->
                        PokemonItem(pokemon = pokemon, navigateToDetail = navigateToDetail)
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonItem(pokemon: PokemonDetail, navigateToDetail: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.75f)
            .clickable { navigateToDetail(pokemon.name) },
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.5.dp, Color.Black),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(pokemon.sprites.frontDefault),
                contentDescription = pokemon.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Fit
            )
            Text(
                text = pokemon.name.replaceFirstChar { it.uppercase() },
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
