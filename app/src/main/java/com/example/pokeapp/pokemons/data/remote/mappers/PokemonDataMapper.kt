package com.example.pokeapp.pokemons.data.remote.mappers

import com.example.pokeapp.common.model.PokemonType
import com.example.pokeapp.pokemons.data.remote.model.RemotePokemon
import com.example.pokeapp.pokemons.domain.model.Pokemon
import javax.inject.Inject

class PokemonDataMapper @Inject constructor() {

    fun RemotePokemon.toPokemon(): Pokemon {
        return Pokemon(
            id = id,
            name = name,
            frontDefaultUrl = sprites.other.officialArtwork.frontDefaultUrl,
            types = types.sortedBy { it.slot }.map { remoteType -> parsePokemonType(remoteType.type.name) }
        )
    }

    private fun parsePokemonType(remoteType: String): PokemonType {
        return when (remoteType) {
            "normal" -> PokemonType.NORMAL
            "fighting" -> PokemonType.FIGHTING
            "flying" -> PokemonType.FLYING
            "poison" -> PokemonType.POISON
            "ground" -> PokemonType.GROUND
            "rock" -> PokemonType.ROCK
            "bug" -> PokemonType.BUG
            "ghost" -> PokemonType.GHOST
            "steel" -> PokemonType.STEEL
            "fire" -> PokemonType.FIRE
            "water" -> PokemonType.WATER
            "grass" -> PokemonType.GRASS
            "electric" -> PokemonType.ELECTRIC
            "psychic" -> PokemonType.PSYCHIC
            "ice" -> PokemonType.ICE
            "dragon" -> PokemonType.DRAGON
            "dark" -> PokemonType.DARK
            "fairy" -> PokemonType.FAIRY
            else -> PokemonType.NORMAL
        }
    }

}