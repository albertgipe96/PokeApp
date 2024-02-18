package com.example.pokeapp.common.model

import androidx.compose.ui.graphics.Color
import com.example.pokeapp.ui.theme.Icons
import com.example.pokeapp.ui.theme.TypeBug
import com.example.pokeapp.ui.theme.TypeDark
import com.example.pokeapp.ui.theme.TypeDragon
import com.example.pokeapp.ui.theme.TypeElectric
import com.example.pokeapp.ui.theme.TypeFairy
import com.example.pokeapp.ui.theme.TypeFighting
import com.example.pokeapp.ui.theme.TypeFire
import com.example.pokeapp.ui.theme.TypeFlying
import com.example.pokeapp.ui.theme.TypeGhost
import com.example.pokeapp.ui.theme.TypeGrass
import com.example.pokeapp.ui.theme.TypeGround
import com.example.pokeapp.ui.theme.TypeIce
import com.example.pokeapp.ui.theme.TypeNormal
import com.example.pokeapp.ui.theme.TypePoison
import com.example.pokeapp.ui.theme.TypePsychic
import com.example.pokeapp.ui.theme.TypeRock
import com.example.pokeapp.ui.theme.TypeSteel
import com.example.pokeapp.ui.theme.TypeWater

enum class PokemonType(val icon: Icons, val color: Color) {
    BUG(icon = Icons.TYPE_BUG, color = TypeBug),
    DARK(icon = Icons.TYPE_DARK, color = TypeDark),
    DRAGON(icon = Icons.TYPE_DRAGON, color = TypeDragon),
    ELECTRIC(icon = Icons.TYPE_ELECTRIC, color = TypeElectric),
    FAIRY(icon = Icons.TYPE_FAIRY, color = TypeFairy),
    FIGHTING(icon = Icons.TYPE_FIGHTING, color = TypeFighting),
    FIRE(icon = Icons.TYPE_FIRE, color = TypeFire),
    FLYING(icon = Icons.TYPE_FLYING, color = TypeFlying),
    GHOST(icon = Icons.TYPE_GHOST, color = TypeGhost),
    GRASS(icon = Icons.TYPE_GRASS, color = TypeGrass),
    GROUND(icon = Icons.TYPE_GROUND, color = TypeGround),
    ICE(icon = Icons.TYPE_ICE, color = TypeIce),
    NORMAL(icon = Icons.TYPE_NORMAL, color = TypeNormal),
    POISON(icon = Icons.TYPE_POISON, color = TypePoison),
    PSYCHIC(icon = Icons.TYPE_PSYCHIC, color = TypePsychic),
    ROCK(icon = Icons.TYPE_ROCK, color = TypeRock),
    STEEL(icon = Icons.TYPE_STEEL, color = TypeSteel),
    WATER(icon = Icons.TYPE_WATER, color = TypeWater)
}