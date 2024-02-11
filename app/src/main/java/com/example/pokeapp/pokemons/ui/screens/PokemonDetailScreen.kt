package com.example.pokeapp.pokemons.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokeapp.pokemons.ui.presentation.PokemonDetailViewModel

@Composable
fun PokemonDetailScreen() {
    val pokemonDetailViewModel = hiltViewModel<PokemonDetailViewModel>()
    Column {
        Button(onClick = { pokemonDetailViewModel.navigateBack() }) {
            Text(text = "Navigate back")
        }
    }
}