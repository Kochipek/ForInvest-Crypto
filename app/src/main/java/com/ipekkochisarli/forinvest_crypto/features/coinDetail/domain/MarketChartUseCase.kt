package com.ipekkochisarli.forinvest_crypto.features.coinDetail.domain

import com.ipekkochisarli.forinvest_crypto.core.data.remote.ApiResult
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.data.ChartPoint
import com.ipekkochisarli.forinvest_crypto.features.home.domain.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarketChartUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    operator fun invoke(coinId: String, days: Int): Flow<ApiResult<List<ChartPoint>>> {
        return repository.getMarketChart(coinId, days)
    }
}