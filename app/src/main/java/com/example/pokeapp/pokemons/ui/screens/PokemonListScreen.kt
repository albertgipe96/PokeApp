package com.example.pokeapp.pokemons.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokeapp.common.ui.navigation.NavigationIntent
import com.example.pokeapp.pokemons.ui.presentation.PokemonListViewModel
import kotlinx.coroutines.channels.Channel

@Composable
fun PokemonListScreen() {
    val pokemonListViewModel = hiltViewModel<PokemonListViewModel>()
    Column {
        Button(onClick = { pokemonListViewModel.navigateToPokemonDetail("1") }) {
            Text(text = "Navigate to Detail")
        }
    }
}