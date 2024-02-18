package com.example.pokeapp.pokemons.domain.useCases

import androidx.paging.PagingData
import androidx.paging.map
import com.example.pokeapp.pokemons.domain.mappers.PokemonUiMapper
import com.example.pokeapp.pokemons.domain.repositories.PokemonRepository
import com.example.pokeapp.pokemons.ui.model.UiPokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllPokemonsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val pokemonUiMapper: PokemonUiMapper
) {

    operator fun invoke(): Flow<PagingData<UiPokemon>> = with(pokemonUiMapper) {
        val result = pokemonRepository.getAllPokemons()
        return result.map { it.map { it.toUiPokemon() } }
    }

}