package com.example.pokeapp.common.ui.navigation

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.onClosed
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.onSuccess
import timber.log.Timber
import javax.inject.Inject

interface AppNavigator {
    var navigationChannel: Channel<NavigationIntent>?
    fun setCustomNavigationChannel(channel: Channel<NavigationIntent>)

    suspend fun navigateBack(route: String? = null, inclusive: Boolean = false)
    fun tryNavigateBack(route: String? = null, inclusive: Boolean = false)
    suspend fun navigateTo(route: String, popUpToRoute: String? = null, inclusive: Boolean = false, isSingleTop: Boolean = false)
    fun tryNavigateTo(route: String, popUpToRoute: String? = null, inclusive: Boolean = false, isSingleTop: Boolean = false)
}

sealed class NavigationIntent {
    data class NavigateBack(val route: String? = null, val inclusive: Boolean = false) : NavigationIntent()
    data class NavigateTo(val route: String, val popUpToRoute: String? = null, val inclusive: Boolean = false, val isSingleTop: Boolean = false) : NavigationIntent()
}

class AppNavigatorImpl @Inject constructor() : AppNavigator {
    override var navigationChannel: Channel<NavigationIntent>? = null
    override fun setCustomNavigationChannel(channel: Channel<NavigationIntent>) {
        navigationChannel = channel
    }

    override suspend fun navigateBack(route: String?, inclusive: Boolean) {
       navigationChannel?.send(NavigationIntent.NavigateBack(route, inclusive))
    }

    override fun tryNavigateBack(route: String?, inclusive: Boolean) {
        navigationChannel?.trySend(NavigationIntent.NavigateBack(route, inclusive))
    }

    override suspend fun navigateTo(route: String, popUpToRoute: String?, inclusive: Boolean, isSingleTop: Boolean) {
        navigationChannel?.send(NavigationIntent.NavigateTo(route, popUpToRoute, inclusive, isSingleTop))
    }

    override fun tryNavigateTo(route: String, popUpToRoute: String?, inclusive: Boolean, isSingleTop: Boolean) {
        navigationChannel?.trySend(NavigationIntent.NavigateTo(route, popUpToRoute, inclusive, isSingleTop))?.onSuccess {
            Timber.d("Navigation to route: $route - Success")
        }?.onFailure {
            Timber.d("Navigation to route: $route - Error: ${it?.message}")
        }?.onClosed {
            Timber.d("Navigation to route: $route - Error: ${it?.message}")
        }
    }
}