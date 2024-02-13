package com.example.pokeapp.pokemons.data.remote.dataSource

import com.example.pokeapp.common.data.networkResult.NetworkResult
import com.example.pokeapp.common.data.networkResult.onError
import com.example.pokeapp.common.data.networkResult.onException
import com.example.pokeapp.common.data.networkResult.onSuccess
import com.example.pokeapp.pokemons.data.remote.PokemonApi
import com.example.pokeapp.pokemons.data.remote.mappers.PokemonDataMapper
import com.example.pokeapp.pokemons.data.remote.model.RemotePokemon
import com.example.pokeapp.pokemons.domain.model.Pokemon
import com.example.pokeapp.pokemons.domain.model.Resource
import com.example.pokeapp.pokemons.domain.model.onError
import com.example.pokeapp.pokemons.domain.model.onException
import com.example.pokeapp.pokemons.domain.model.onSuccess
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

interface RemotePokemonDataSource {
    suspend fun getAllPokemons(): Resource<List<Pokemon>>
    suspend fun getPokemonInfo(id: String): Resource<Pokemon>
}

class RemotePokemonDataSourceImpl @Inject constructor(
    private val pokemonApi: PokemonApi,
    private val pokemonDataMapper: PokemonDataMapper
): RemotePokemonDataSource {

    override suspend fun getAllPokemons(): Resource<List<Pokemon>> {
        val result = pokemonApi.getAllPokemons()
        return when (result) {
            is NetworkResult.Success -> {
                val pokemonList = mutableListOf<Pokemon>()
                result.data.results.forEach {
                    val pokemon = getPokemonInfo(it.name)
                    val success = suspendCancellableCoroutine { continuation ->
                        pokemon
                            .onSuccess { continuation.resume(it) }
                            .onError { continuation.resume(null) }
                            .onException { continuation.resume(null) }
                    }
                    if (success != null) pokemonList.add(success)
                }
                Resource.Success(pokemonList)
            }
            is NetworkResult.Error -> Resource.Error(result.message)
            is NetworkResult.Exception -> Resource.Exception(result.e)
        }
    }

    override suspend fun getPokemonInfo(id: String): Resource<Pokemon> = with(pokemonDataMapper) {
        val result = pokemonApi.getPokemonInfo(id)
        return suspendCancellableCoroutine { continuation ->
            result
                .onSuccess {
                    continuation.resume(Resource.Success(it.toPokemon()))
                }
                .onError { code, message ->
                    continuation.resume(Resource.Error(message))
                }
                .onException { e ->
                    continuation.resume(Resource.Exception(e))
                }
        }
    }

}