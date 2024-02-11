package com.example.pokeapp.common.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.pokeapp.R

sealed class BottomNavItem(val route: String, val icon: ImageVector, @StringRes val label: Int) {
    object Home : BottomNavItem("home", Icons.Default.Home, R.string.tab_bar_home)
    object Types : BottomNavItem("types", Icons.Default.Info, R.string.tab_bar_types)
    object Profile : BottomNavItem("profile", Icons.Default.Person, R.string.tab_bar_profile)
}