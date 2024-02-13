package com.example.pokeapp.pokemons.domain.useCases

import com.example.pokeapp.pokemons.domain.mappers.PokemonUiMapper
import com.example.pokeapp.pokemons.domain.model.onError
import com.example.pokeapp.pokemons.domain.model.onException
import com.example.pokeapp.pokemons.domain.model.onSuccess
import com.example.pokeapp.pokemons.domain.repositories.PokemonRepository
import com.example.pokeapp.pokemons.ui.model.UiPokemon
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class GetAllPokemonsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val pokemonUiMapper: PokemonUiMapper
) {

    suspend operator fun invoke(): List<UiPokemon> = with(pokemonUiMapper) {
        val result = pokemonRepository.getAllPokemons()
        return suspendCancellableCoroutine { continuation ->
            result.onSuccess {
                continuation.resume(it.map { it.toUiPokemon() })
            }.onError { errorMessage ->
                continuation.resume(emptyList())
            }.onException { e ->
                continuation.resume(emptyList())
            }
        }
    }

}