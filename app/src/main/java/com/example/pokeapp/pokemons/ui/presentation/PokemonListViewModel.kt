package com.example.pokeapp.pokemons.ui.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.common.ui.navigation.AppNavigator
import com.example.pokeapp.common.ui.navigation.Destination
import com.example.pokeapp.pokemons.domain.useCases.GetAllPokemonsUseCase
import com.example.pokeapp.pokemons.ui.model.UiPokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val getAllPokemonsUseCase: GetAllPokemonsUseCase
) : ViewModel() {

    val pokemonList: List<UiPokemon>
        get() = _pokemonList.value
    private val _pokemonList = mutableStateOf(emptyList<UiPokemon>())

    init {
        viewModelScope.launch {
            _pokemonList.value = getAllPokemonsUseCase()
        }
    }

    fun navigateToPokemonDetail(id: String) {
        appNavigator.tryNavigateTo(Destination.PokemonDetailScreen(id))
    }

}