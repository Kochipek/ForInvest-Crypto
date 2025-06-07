package com.ipekkochisarli.forinvest_crypto.features.search.di

import com.ipekkochisarli.forinvest_crypto.core.data.local.CoinDao
import com.ipekkochisarli.forinvest_crypto.features.search.data.local.CoinLocalDataSource
import com.ipekkochisarli.forinvest_crypto.features.search.data.local.CoinLocalDataSourceImpl
import com.ipekkochisarli.forinvest_crypto.features.search.data.local.SearchRepositoryImpl
import com.ipekkochisarli.forinvest_crypto.features.search.domain.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class SearchModule {

    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideCoinLocalDataSource(
        coinDao: CoinDao,
        dispatcher: CoroutineDispatcher
    ): CoinLocalDataSource {
        return CoinLocalDataSourceImpl(coinDao, dispatcher)
    }

    @Provides
    fun provideSearchRepository(
        localDataSource: CoinLocalDataSource,
        ): SearchRepository {
        return SearchRepositoryImpl(
            localDataSource
        )
    }
}
