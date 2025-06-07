package com.ipekkochisarli.forinvest_crypto.features.search.data.local

import com.ipekkochisarli.forinvest_crypto.core.data.local.CoinEntity
import com.ipekkochisarli.forinvest_crypto.core.data.local.CoinDao
import com.ipekkochisarli.forinvest_crypto.core.data.remote.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CoinLocalDataSourceImpl(
    private val coinDao: CoinDao,
    private val ioDispatcher: CoroutineDispatcher
) : CoinLocalDataSource {

    override suspend fun insertCoins(coins: List<CoinEntity>): ApiResult<Boolean> {
        return try {
            withContext(ioDispatcher) {
                coinDao.insertAllCoins(*coins.toTypedArray())
            }
            ApiResult.Success(true)
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Failed to insert coins")
        }
    }

    override fun searchCoins(query: String): Flow<ApiResult<List<CoinEntity>>> {
        return coinDao.searchCoins("%${query}%").map { coins ->
            ApiResult.Success(coins)
        }
    }
}
