package com.example.pokeapp.pokemons.domain.model

import com.example.pokeapp.common.model.PokemonType

data class Pokemon(
    val id: Int,
    val name: String,
    val frontDefaultUrl: String,
    val types: List<PokemonType>
)