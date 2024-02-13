package com.example.pokeapp.pokemons.domain.mappers

import com.example.pokeapp.pokemons.domain.model.Pokemon
import com.example.pokeapp.pokemons.ui.model.UiPokemon
import javax.inject.Inject

class PokemonUiMapper @Inject constructor() {

    fun Pokemon.toUiPokemon(): UiPokemon {
        return UiPokemon(
            name = name
        )
    }

}