package com.example.pokeapp.pokemons.ui.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.common.ui.navigation.AppNavigator
import com.example.pokeapp.common.ui.navigation.Destination.PokemonDetailScreen.POKEMON_ID
import com.example.pokeapp.common.ui.state.ViewStateDelegate
import com.example.pokeapp.common.ui.state.ViewStateDelegateImpl
import com.example.pokeapp.pokemons.domain.useCases.GetPokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val appNavigator: AppNavigator,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel(), ViewStateDelegate<PokemonDetailUiState> by ViewStateDelegateImpl(PokemonDetailUiState.Loading) {

    private val pokemonId: String
        get() {
            return requireNotNull(savedStateHandle.get<String>(POKEMON_ID))
        }

    init {
        viewModelScope.launch {
            val pokemonDetail = getPokemonDetailUseCase(pokemonId)
            reduce { PokemonDetailUiState.Loaded(pokemonDetail) }
        }
    }

    fun navigateBack() {
        appNavigator.tryNavigateBack()
    }

}