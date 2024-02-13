package com.example.pokeapp.pokemons.di

import com.example.pokeapp.pokemons.data.remote.PokemonApi
import com.example.pokeapp.pokemons.data.remote.dataSource.RemotePokemonDataSource
import com.example.pokeapp.pokemons.data.remote.dataSource.RemotePokemonDataSourceImpl
import com.example.pokeapp.pokemons.domain.repositories.PokemonRepository
import com.example.pokeapp.pokemons.domain.repositories.PokemonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object PokemonModule {
    
    @Provides
    @ViewModelScoped
    fun providesRemotePokemonDataSource(pokemonApi: PokemonApi): RemotePokemonDataSource =
        RemotePokemonDataSourceImpl(pokemonApi)

    @Provides
    @ViewModelScoped
    fun providesPokemonRepository(remotePokemonDataSource: RemotePokemonDataSource): PokemonRepository =
        PokemonRepositoryImpl(remotePokemonDataSource)
    
}