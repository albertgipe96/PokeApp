package com.example.pokeapp.pokemons.ui.presentation

import androidx.lifecycle.ViewModel
import com.example.pokeapp.common.ui.navigation.AppNavigator
import com.example.pokeapp.common.ui.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    fun navigateToPokemonDetail(id: String) {
        appNavigator.tryNavigateTo(Destination.PokemonDetailScreen(id))
    }

}