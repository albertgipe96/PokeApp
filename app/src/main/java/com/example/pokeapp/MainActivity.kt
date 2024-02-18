package com.example.pokeapp

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pokeapp.common.ui.navigation.AppNavigator
import com.example.pokeapp.common.ui.navigation.BottomNavigationBar
import com.example.pokeapp.common.ui.navigation.Destination
import com.example.pokeapp.common.ui.navigation.NavHost
import com.example.pokeapp.common.ui.navigation.NavigationIntent
import com.example.pokeapp.common.ui.navigation.composable
import com.example.pokeapp.pokemons.ui.screens.PokemonDetailScreen
import com.example.pokeapp.pokemons.ui.screens.PokemonListScreen
import com.example.pokeapp.ui.theme.PokeAppTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val mainViewModel = hiltViewModel<MainViewModel>()
    val navController = rememberNavController()

    NavigationEffects(
        navigationChannel = mainViewModel.appNavigator.navigationChannel,
        navHostController = navController
    )
    PokeAppTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            bottomBar = { BottomNavigationBar(navController = navController) }
        ) { paddingValues ->
            NavHost(navController = navController, startDestination = Destination.HomeScreen, modifier = Modifier.padding(paddingValues)) {
                composable(destination = Destination.HomeScreen) {
                    PokemonListScreen()
                }
                composable(destination = Destination.TypesScreen) {
                    Text(text = "Types Screen")
                }
                composable(destination = Destination.ProfileScreen) {
                    Text(text = "Profile Screen")
                }
                composable(destination = Destination.PokemonDetailScreen) {
                    PokemonDetailScreen()
                }
            }
        }
    }
}

@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>?,
    navHostController: NavHostController
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel) {
        navigationChannel?.receiveAsFlow()?.collect { intent ->
            if (activity?.isFinishing == true) return@collect
            when (intent) {
                is NavigationIntent.NavigateBack -> {
                    intent.route?.let {
                        navHostController.popBackStack(intent.route, intent.inclusive)
                    } ?: navHostController.popBackStack()
                }
                is NavigationIntent.NavigateTo -> {
                    navHostController.navigate(intent.route) {
                        launchSingleTop = intent.isSingleTop
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) { inclusive = intent.inclusive }
                        }
                    }
                }
            }
        }
    }
}

@HiltViewModel
class MainViewModel @Inject constructor(
    val appNavigator: AppNavigator
) : ViewModel() {
    init {
        appNavigator.setCustomNavigationChannel(Channel<NavigationIntent>(capacity = Int.MAX_VALUE, onBufferOverflow = BufferOverflow.DROP_LATEST))
    }
}