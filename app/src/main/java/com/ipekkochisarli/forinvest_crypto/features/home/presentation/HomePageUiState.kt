package com.ipekkochisarli.forinvest_crypto.features.home.presentation

import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.CoinUiModel
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.TrendingCoinUiModel

data class HomePageUiState (
    val coinList: List<CoinUiModel> = emptyList(),
    val trendingCoinList: List<TrendingCoinUiModel> = emptyList(),
    val isLoading: Boolean = false,
    val message: String? = null
)