package com.example.pokeapp.common.di

import com.example.pokeapp.common.data.networkResult.NetworkResultCallAdapterFactory
import com.example.pokeapp.pokemons.data.remote.PokemonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {

    @Provides
    @ViewModelScoped
    fun providesApiClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Timber.tag("Network").d(message) }).apply { level = HttpLoggingInterceptor.Level.BODY }
            )
            .build()

    @Provides
    @ViewModelScoped
    fun providesPokemonApi(okHttpClient: OkHttpClient): PokemonApi =
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResultCallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(PokemonApi::class.java)

}