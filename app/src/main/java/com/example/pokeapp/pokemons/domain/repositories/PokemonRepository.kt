package com.example.pokeapp.pokemons.domain.repositories

import com.example.pokeapp.common.data.networkResult.onError
import com.example.pokeapp.common.data.networkResult.onException
import com.example.pokeapp.common.data.networkResult.onSuccess
import com.example.pokeapp.pokemons.data.remote.PokemonApi
import com.example.pokeapp.pokemons.data.remote.dataSource.RemotePokemonDataSource
import com.example.pokeapp.pokemons.domain.model.Pokemon
import com.example.pokeapp.pokemons.domain.model.Resource
import com.example.pokeapp.pokemons.domain.model.onError
import com.example.pokeapp.pokemons.domain.model.onException
import com.example.pokeapp.pokemons.domain.model.onSuccess
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

interface PokemonRepository {
    suspend fun getAllPokemons(): Resource<List<Pokemon>>
}

class PokemonRepositoryImpl @Inject constructor(
    private val remotePokemonDataSource: RemotePokemonDataSource
): PokemonRepository {

    override suspend fun getAllPokemons(): Resource<List<Pokemon>> { // Manage get from remote data source or from local data source
        val response = remotePokemonDataSource.getAllPokemons()
        return suspendCancellableCoroutine { continuation ->
            response.onSuccess {
                continuation.resume(Resource.Success(it))
            }.onError { errorMessage ->
                continuation.resume(Resource.Error(errorMessage))
            }.onException { e ->
                continuation.resume(Resource.Exception(e))
            }
        }
    }

}