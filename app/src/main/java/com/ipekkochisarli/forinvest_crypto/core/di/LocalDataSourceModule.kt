package com.ipekkochisarli.forinvest_crypto.core.di

import android.content.Context
import androidx.room.Room
import com.ipekkochisarli.forinvest_crypto.core.data.local.CoinDatabase
import com.ipekkochisarli.forinvest_crypto.core.data.local.PreferencesManager
import com.ipekkochisarli.forinvest_crypto.util.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CoinDatabase {
        return Room.databaseBuilder(
            context,
            CoinDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePreferencesManager(@ApplicationContext context: Context): PreferencesManager =
        PreferencesManager(context)
}