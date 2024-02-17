package com.example.pokeapp.pokemons.ui.presentation

import com.example.pokeapp.pokemons.ui.model.UiPokemon

sealed class PokemonListUiState {
    data object Loading : PokemonListUiState()
    data object Error : PokemonListUiState()
    data class Loaded(val pokemonList: List<UiPokemon>) : PokemonListUiState()
}