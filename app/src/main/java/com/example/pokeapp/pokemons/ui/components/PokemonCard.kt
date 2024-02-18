package com.example.pokeapp.pokemons.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokeapp.common.model.PokemonType
import com.example.pokeapp.pokemons.ui.model.UiPokemon
import com.example.pokeapp.ui.theme.Shape
import com.example.pokeapp.ui.theme.Spacing
import com.example.pokeapp.ui.theme.neutral_300
import com.example.pokeapp.ui.theme.white

@Composable
fun PokemonCard(pokemon: UiPokemon, onCardClicked: (id: Int) -> Unit) {
    PokemonCard(id = pokemon.id, name = pokemon.name, imageUrl = pokemon.frontDefaultUrl, types = pokemon.types, onCardClicked = onCardClicked)
}

@Composable
fun PokemonCard(
    id: Int,
    name: String,
    imageUrl: String,
    types: List<PokemonType>,
    onCardClicked: (id: Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.MEDIUM.spacing, vertical = Spacing.EXTRA_SMALL.spacing)
            .clickable { onCardClicked(id) }
            .background(white, Shape.large.shape)
            .border(1.dp, neutral_300, Shape.large.shape)
            .padding(Spacing.SMALL.spacing),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            modifier = Modifier.size(40.dp),
            model = ImageRequest.Builder(LocalContext.current).data(imageUrl).crossfade(true).build(),
            contentDescription = null
        )
        Text(text = "$name (#$id)")
        Row {
            types.forEach { type -> Icon(modifier = Modifier.size(20.dp), painter = painterResource(id = type.icon.drawable), tint = type.color, contentDescription = null) }
        }
    }
}