package com.example.pokeapp.pokemons.data.remote.dataSource

import com.example.pokeapp.common.data.networkResult.onError
import com.example.pokeapp.common.data.networkResult.onException
import com.example.pokeapp.common.data.networkResult.onSuccess
import com.example.pokeapp.pokemons.data.remote.PokemonApi
import com.example.pokeapp.pokemons.domain.model.Pokemon
import com.example.pokeapp.pokemons.domain.model.Resource
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

interface RemotePokemonDataSource {
    suspend fun getAllPokemons(): Resource<List<Pokemon>>
}

class RemotePokemonDataSourceImpl @Inject constructor(
    private val pokemonApi: PokemonApi
): RemotePokemonDataSource {

    override suspend fun getAllPokemons(): Resource<List<Pokemon>> {
        val result = pokemonApi.getAllPokemons()
        return suspendCancellableCoroutine { continuation ->
            result
                .onSuccess {
                    continuation.resume(Resource.Success(it.results.map { Pokemon(it.name) }))
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