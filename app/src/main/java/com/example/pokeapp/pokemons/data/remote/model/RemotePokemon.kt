package com.example.pokeapp.pokemons.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemotePokemon(
    val id: Int,
    val name: String,
    val sprites: RemotePokemonSprite,
    val types: List<RemotePokemonTypeData>
)

data class RemotePokemonSprite(
    val other: RemotePokemonSpriteOther
)

data class RemotePokemonSpriteOther(
    @SerializedName("official-artwork") val officialArtwork: RemotePokemonSpriteOtherOfficialArtwork
)

data class RemotePokemonSpriteOtherOfficialArtwork(
    @SerializedName("front_default") val frontDefaultUrl: String
)

data class RemotePokemonTypeData(
    val slot: Int,
    val type: RemotePokemonType
)

data class RemotePokemonType(
    val name: String
)