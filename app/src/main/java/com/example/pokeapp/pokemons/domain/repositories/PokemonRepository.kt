package com.example.pokeapp.pokemons.domain.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokeapp.pokemons.data.remote.dataSource.RemotePokemonDataSource
import com.example.pokeapp.pokemons.domain.model.Pokemon
import com.example.pokeapp.pokemons.domain.model.Resource
import com.example.pokeapp.pokemons.domain.model.onError
import com.example.pokeapp.pokemons.domain.model.onException
import com.example.pokeapp.pokemons.domain.model.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

interface PokemonRepository {
    fun getAllPokemons(): Flow<PagingData<Pokemon>>
    suspend fun getPokemonById(id: String): Resource<Pokemon>
}

class PokemonRepositoryImpl @Inject constructor(
    private val remotePokemonDataSource: RemotePokemonDataSource
): PokemonRepository {

    override fun getAllPokemons(): Flow<PagingData<Pokemon>> { // Manage get from remote data source or from local data source
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { remotePokemonDataSource.getAllPokemons() }
        ).flow
    }

    override suspend fun getPokemonById(id: String): Resource<Pokemon> { // Manage get from remote data source or from local data source
        val result = remotePokemonDataSource.getPokemonInfo(id)
        return suspendCancellableCoroutine { continuation ->
            result
                .onSuccess { continuation.resume(Resource.Success(it)) }
                .onError { errorMessage -> continuation.resume(Resource.Error(errorMessage)) }
                .onException { e -> continuation.resume(Resource.Exception(e)) }
        }
    }
}