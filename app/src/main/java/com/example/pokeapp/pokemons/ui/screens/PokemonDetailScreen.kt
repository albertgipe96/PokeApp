package com.example.pokeapp.pokemons.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokeapp.common.ui.state.collectWithLifecycle
import com.example.pokeapp.pokemons.ui.presentation.PokemonDetailUiState
import com.example.pokeapp.pokemons.ui.presentation.PokemonDetailViewModel

@Composable
fun PokemonDetailScreen() {
    val viewModel = hiltViewModel<PokemonDetailViewModel>()
    val uiState by viewModel.collectWithLifecycle()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val state = uiState) {
            PokemonDetailUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            PokemonDetailUiState.Error -> {}
            is PokemonDetailUiState.Loaded -> {
                AsyncImage(
                    modifier = Modifier.size(100.dp),
                    model = ImageRequest.Builder(LocalContext.current).data(state.pokemonDetail.mainImageUrl).crossfade(true).build(),
                    contentDescription = null
                )
                Text(text = state.pokemonDetail.name)
            }
        }
    }
}