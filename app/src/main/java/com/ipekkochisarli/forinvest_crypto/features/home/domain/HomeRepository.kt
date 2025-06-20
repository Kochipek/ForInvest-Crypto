package com.ipekkochisarli.forinvest_crypto.features.home.domain

import com.ipekkochisarli.forinvest_crypto.core.data.remote.ApiResult
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.data.ChartPoint
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.data.MarketDataDto
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.domain.CoinDetailUiModel
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.CoinUiModel
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.TrendingCoinUiModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getCoins(currency: String): Flow<ApiResult<List<CoinUiModel>>>
    suspend fun getTrendingCoins(): Flow<ApiResult<List<TrendingCoinUiModel>>>
    fun searchCoins(query: String) : Flow<ApiResult<List<CoinUiModel>>>
    suspend fun getCoinDetail(id: String): Flow<ApiResult<CoinDetailUiModel>>
    fun getMarketChart(id: String, day: Int): Flow<ApiResult<List<ChartPoint>>>
}