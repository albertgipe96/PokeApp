package com.example.pokeapp.pokemons.ui.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.common.ui.navigation.AppNavigator
import com.example.pokeapp.common.ui.navigation.Destination
import com.example.pokeapp.common.ui.state.ViewStateDelegate
import com.example.pokeapp.common.ui.state.ViewStateDelegateImpl
import com.example.pokeapp.pokemons.domain.useCases.GetAllPokemonsUseCase
import com.example.pokeapp.pokemons.ui.model.UiPokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val getAllPokemonsUseCase: GetAllPokemonsUseCase
) : ViewModel(), ViewStateDelegate<PokemonListUiState> by ViewStateDelegateImpl(PokemonListUiState.Loading) {

    init {
        viewModelScope.launch {
            val pokemonList = getAllPokemonsUseCase()
            reduce { PokemonListUiState.Loaded(pokemonList) }
        }
    }

    fun navigateToPokemonDetail(id: Int) {
        appNavigator.tryNavigateTo(Destination.PokemonDetailScreen(id.toString()))
    }

}