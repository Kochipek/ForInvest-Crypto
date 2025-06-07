package com.ipekkochisarli.forinvest_crypto.features.search.data.local

import com.ipekkochisarli.forinvest_crypto.core.data.local.CoinEntity
import com.ipekkochisarli.forinvest_crypto.core.data.remote.ApiResult
import kotlinx.coroutines.flow.Flow

interface CoinLocalDataSource {
    suspend fun insertCoins(coins: List<CoinEntity>): ApiResult<Boolean>
    fun searchCoins(query: String): Flow<ApiResult<List<CoinEntity>>>
}