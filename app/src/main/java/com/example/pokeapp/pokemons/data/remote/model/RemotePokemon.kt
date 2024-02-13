package com.example.pokeapp.pokemons.data.remote.model

data class RemotePokemon(
    val name: String
)

data class RemotePokemonData(
    val results: List<RemotePokemon>
)