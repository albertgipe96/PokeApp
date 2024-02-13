package com.example.pokeapp.pokemons.data.remote.mappers

import com.example.pokeapp.pokemons.data.remote.model.RemotePokemon
import com.example.pokeapp.pokemons.domain.model.Pokemon
import javax.inject.Inject

class PokemonDataMapper @Inject constructor() {

    fun RemotePokemon.toPokemon(): Pokemon {
        return Pokemon(
            name = name
        )
    }

}