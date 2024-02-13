package com.example.pokeapp.pokemons.data.remote

import com.example.pokeapp.common.data.networkResult.NetworkResult
import com.example.pokeapp.pokemons.data.remote.model.RemotePokemon
import com.example.pokeapp.pokemons.data.remote.model.RemotePokemonData
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon")
    suspend fun getAllPokemons(): NetworkResult<RemotePokemonData>

    @GET("pokemon/{id}")
    suspend fun getPokemonInfo(
        @Path("id") id: String
    ): NetworkResult<RemotePokemon>


}