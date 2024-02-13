package com.example.pokeapp.pokemons.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokeapp.common.ui.navigation.NavigationIntent
import com.example.pokeapp.pokemons.ui.presentation.PokemonListViewModel
import kotlinx.coroutines.channels.Channel

@Composable
fun PokemonListScreen() {
    val pokemonListViewModel = hiltViewModel<PokemonListViewModel>()
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(pokemonListViewModel.pokemonList) {
                Text(text = it.name)
            }
        }
    }
}