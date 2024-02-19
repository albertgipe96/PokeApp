package com.example.pokeapp.pokemons.ui.model

import com.example.pokeapp.common.model.PokemonType

data class PokemonDetailUiModel(
    val id: Int,
    val name: String,
    val mainImageUrl: String,
    val types: List<PokemonType>
)
