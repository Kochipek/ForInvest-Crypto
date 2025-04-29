package com.ipekkochisarli.forinvest_crypto.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import jakarta.inject.Qualifier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
object DispatcherModule {

    @Dispatcher(DispatcherType.Io)
    @Provides
    @ViewModelScoped
    fun provideDispatcherIo(): CoroutineDispatcher = Dispatchers.IO

    @Dispatcher(DispatcherType.Unconfined)
    @Provides
    @ViewModelScoped
    fun provideDispatcherUnconfined(): CoroutineDispatcher = Dispatchers.Unconfined

    @Dispatcher(DispatcherType.Default)
    @Provides
    @ViewModelScoped
    fun provideDispatcherDefault(): CoroutineDispatcher = Dispatchers.Default

    @Dispatcher(DispatcherType.Main)
    @Provides
    @ViewModelScoped
    fun provideDispatcherMain(): CoroutineDispatcher = Dispatchers.Main

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class Dispatcher(val type: DispatcherType)

enum class DispatcherType {
    Main,
    Io,
    Default,
    Unconfined
}
