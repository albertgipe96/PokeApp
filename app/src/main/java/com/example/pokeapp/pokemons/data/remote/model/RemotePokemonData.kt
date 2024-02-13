package com.example.pokeapp.pokemons.data.remote.model

data class RemotePokemonData(
    val next: String?,
    val results: List<RemotePokemonDataInfo>
)

data class RemotePokemonDataInfo(
    val name: String
)
