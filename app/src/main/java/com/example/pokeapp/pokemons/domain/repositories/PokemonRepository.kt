package com.example.pokeapp.pokemons.domain.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokeapp.pokemons.data.remote.dataSource.RemotePokemonDataSource
import com.example.pokeapp.pokemons.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PokemonRepository {
    fun getAllPokemons(): Flow<PagingData<Pokemon>>
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

}