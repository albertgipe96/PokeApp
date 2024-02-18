package com.example.pokeapp.pokemons.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.pokeapp.common.ui.state.collectWithLifecycle
import com.example.pokeapp.pokemons.ui.components.PokemonCard
import com.example.pokeapp.pokemons.ui.model.UiPokemon
import com.example.pokeapp.pokemons.ui.presentation.PokemonListUiState
import com.example.pokeapp.pokemons.ui.presentation.PokemonListViewModel

@Composable
fun PokemonListScreen() {
    val viewModel = hiltViewModel<PokemonListViewModel>()
    val uiState by viewModel.collectWithLifecycle()
    Column(modifier = Modifier.fillMaxSize()) {
        when (val state = uiState) {
            PokemonListUiState.Loading -> {}
            PokemonListUiState.Error -> {}
            is PokemonListUiState.Loaded -> {
                val pokemonPagingItems: LazyPagingItems<UiPokemon> = state.pokemonList.collectAsLazyPagingItems()
                when (pokemonPagingItems.loadState.refresh) {
                    is LoadState.Error -> {
                        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Error fetching pokemon data")
                        }
                    }
                    LoadState.Loading -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                    is LoadState.NotLoading -> {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(pokemonPagingItems.itemCount) { index ->
                                PokemonCard(
                                    pokemon = pokemonPagingItems[index]!!,
                                    onCardClicked = { id -> viewModel.navigateToPokemonDetail(id) }
                                )
                            }
                            when (pokemonPagingItems.loadState.append) {
                                is LoadState.Error -> {
                                    item {
                                        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                                            Text(text = "Error fetching new pokemon data")
                                        }
                                    }
                                }
                                LoadState.Loading -> {
                                    item {
                                        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                                            CircularProgressIndicator()
                                        }
                                    }
                                }
                                is LoadState.NotLoading -> {}
                            }
                        }
                    }
                }
            }
        }
    }
}