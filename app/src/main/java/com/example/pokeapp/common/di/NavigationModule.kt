package com.example.pokeapp.common.di

import com.example.pokeapp.common.ui.navigation.AppNavigator
import com.example.pokeapp.common.ui.navigation.AppNavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    @Singleton
    fun providesAppNavigator(): AppNavigator = AppNavigatorImpl()

}