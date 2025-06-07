package com.ipekkochisarli.forinvest_crypto.features.home.di

import com.ipekkochisarli.forinvest_crypto.core.data.local.CoinDao
import com.ipekkochisarli.forinvest_crypto.core.data.local.CoinDatabase
import com.ipekkochisarli.forinvest_crypto.features.home.data.remote.HomePageApi
import com.ipekkochisarli.forinvest_crypto.features.home.data.remote.HomeRepositoryImpl
import com.ipekkochisarli.forinvest_crypto.features.home.domain.HomeRepository
import com.ipekkochisarli.forinvest_crypto.features.search.data.local.CoinLocalDataSource
import com.ipekkochisarli.forinvest_crypto.features.search.data.local.CoinLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Provides
    fun provideHomeApiService(retrofit: Retrofit): HomePageApi {
        return retrofit.create(HomePageApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinLocalDataSource(
        coinDao: CoinDao
    ): CoinLocalDataSource {
        return CoinLocalDataSourceImpl(coinDao)
    }

    @Provides
    @Singleton
    fun provideCoinDao(database: CoinDatabase): CoinDao {
        return database.coinDao()
    }

    @Provides
    fun provideHomeRepository(
        homePageApi: HomePageApi,
        localDataSource: CoinLocalDataSource
    ): HomeRepository {
        return HomeRepositoryImpl(homePageApi, localDataSource)
    }
}
