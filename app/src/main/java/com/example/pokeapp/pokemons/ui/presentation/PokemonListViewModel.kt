package com.example.pokeapp.pokemons.ui.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pokeapp.common.ui.navigation.AppNavigator
import com.example.pokeapp.common.ui.navigation.Destination
import com.example.pokeapp.common.ui.state.ViewStateDelegate
import com.example.pokeapp.common.ui.state.ViewStateDelegateImpl
import com.example.pokeapp.pokemons.domain.useCases.GetAllPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val getAllPokemonsUseCase: GetAllPokemonsUseCase
) : ViewModel(), ViewStateDelegate<PokemonListUiState> by ViewStateDelegateImpl(PokemonListUiState.Loading) {

    init {
        viewModelScope.launch {
            getAllPokemonsUseCase()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect { pokemonPagingData ->
                    reduce { PokemonListUiState.Loaded(MutableStateFlow(pokemonPagingData)) }
                }
        }
    }

    fun navigateToPokemonDetail(id: Int) {
        appNavigator.tryNavigateTo(Destination.PokemonDetailScreen(id.toString()))
    }

}