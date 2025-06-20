package com.ipekkochisarli.forinvest_crypto.features.coinDetail.presentation

import com.ipekkochisarli.forinvest_crypto.features.coinDetail.data.ChartPoint
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.domain.CoinChartTimeSpan
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.domain.CoinDetailUiModel

data class CoinDetailUiState(
    val pricePoints: List<ChartPoint> = emptyList(),
    val timeRange: CoinChartTimeSpan = CoinChartTimeSpan.TIMESPAN_7DAYS,
    val coinDetail: CoinDetailUiModel? = null,
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)