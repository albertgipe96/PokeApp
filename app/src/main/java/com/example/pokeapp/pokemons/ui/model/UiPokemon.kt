package com.example.pokeapp.pokemons.ui.model

import com.example.pokeapp.common.model.PokemonType

data class UiPokemon(
    val id: Int,
    val name: String,
    val frontDefaultUrl: String,
    val types: List<PokemonType>
)
