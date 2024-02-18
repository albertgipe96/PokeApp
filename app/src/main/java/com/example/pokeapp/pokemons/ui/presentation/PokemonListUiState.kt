package com.example.pokeapp.pokemons.ui.presentation

import androidx.paging.PagingData
import com.example.pokeapp.pokemons.ui.model.UiPokemon
import kotlinx.coroutines.flow.MutableStateFlow

sealed class PokemonListUiState {
    data object Loading : PokemonListUiState()
    data object Error : PokemonListUiState()
    data class Loaded(val pokemonList: MutableStateFlow<PagingData<UiPokemon>>) : PokemonListUiState()
}