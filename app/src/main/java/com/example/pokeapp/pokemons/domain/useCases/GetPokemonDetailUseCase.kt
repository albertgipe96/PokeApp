package com.example.pokeapp.pokemons.domain.useCases

import com.example.pokeapp.pokemons.domain.mappers.PokemonUiMapper
import com.example.pokeapp.pokemons.domain.model.onError
import com.example.pokeapp.pokemons.domain.model.onException
import com.example.pokeapp.pokemons.domain.model.onSuccess
import com.example.pokeapp.pokemons.domain.repositories.PokemonRepository
import com.example.pokeapp.pokemons.ui.model.PokemonDetailUiModel
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class GetPokemonDetailUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val pokemonUiMapper: PokemonUiMapper
) {

    suspend operator fun invoke(id: String): PokemonDetailUiModel = with(pokemonUiMapper) {
        val result = pokemonRepository.getPokemonById(id)
        return suspendCancellableCoroutine { continuation ->
            result
                .onSuccess { continuation.resume(it.toPokemonDetailUiModel()) }
                .onError { errorMessage ->  }
                .onException { e ->  }
        }
    }

}