package com.example.pokeapp.pokemons.ui.presentation

import androidx.lifecycle.ViewModel
import com.example.pokeapp.common.ui.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    fun navigateBack() {
        appNavigator.tryNavigateBack()
    }

}