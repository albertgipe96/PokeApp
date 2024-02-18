package com.example.pokeapp.pokemons.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokeapp.common.ui.state.collectWithLifecycle
import com.example.pokeapp.pokemons.ui.components.PokemonCard
import com.example.pokeapp.pokemons.ui.presentation.PokemonListUiState
import com.example.pokeapp.pokemons.ui.presentation.PokemonListViewModel

@Composable
fun PokemonListScreen() {
    val viewModel = hiltViewModel<PokemonListViewModel>()
    val uiState by viewModel.collectWithLifecycle()
    Column(modifier = Modifier.fillMaxSize()) {
        when (val state = uiState) {
            PokemonListUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            PokemonListUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error loading pokemon")
                }
            }
            is PokemonListUiState.Loaded -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.pokemonList) { pokemon ->
                        PokemonCard(
                            pokemon = pokemon,
                            onCardClicked = { id -> viewModel.navigateToPokemonDetail(id) }
                        )
                    }
                }
            }
        }
    }
}