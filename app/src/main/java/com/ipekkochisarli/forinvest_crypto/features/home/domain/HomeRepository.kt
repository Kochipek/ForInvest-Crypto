package com.ipekkochisarli.forinvest_crypto.features.home.domain

import com.ipekkochisarli.forinvest_crypto.core.data.remote.ApiResult
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.CoinUiModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getCoins(currency: String): Flow<ApiResult<List<CoinUiModel>>>
    fun getTrendingCoins(): Flow<ApiResult<List<CoinUiModel>>>
}