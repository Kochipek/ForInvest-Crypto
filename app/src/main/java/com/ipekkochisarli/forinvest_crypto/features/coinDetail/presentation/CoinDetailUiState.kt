package com.ipekkochisarli.forinvest_crypto.features.coinDetail.presentation

import com.ipekkochisarli.forinvest_crypto.features.coinDetail.domain.CoinChartTimeSpan
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.domain.CoinDetailUiModel

enum class CoinDetailUiState (
    val priceList: List<List<Double>> = emptyList(),
    val timeRange: CoinChartTimeSpan = CoinChartTimeSpan.TIMESPAN_7DAYS,
    val coinDetail: CoinDetailUiModel? = null
)