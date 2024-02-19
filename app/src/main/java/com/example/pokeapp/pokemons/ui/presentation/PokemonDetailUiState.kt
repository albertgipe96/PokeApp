package com.example.pokeapp.pokemons.ui.presentation

import com.example.pokeapp.pokemons.ui.model.PokemonDetailUiModel

sealed class PokemonDetailUiState {
    data object Loading : PokemonDetailUiState()
    data object Error : PokemonDetailUiState()
    data class Loaded(val pokemonDetail: PokemonDetailUiModel) : PokemonDetailUiState()
}