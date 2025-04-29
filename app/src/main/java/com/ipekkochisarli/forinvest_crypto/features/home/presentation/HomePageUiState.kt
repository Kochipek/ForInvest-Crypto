package com.ipekkochisarli.forinvest_crypto.features.home.presentation

import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.CoinUiModel

data class HomePageUiState (
    val coinList: List<CoinUiModel> = emptyList(),
    val isLoading: Boolean = false,
    val message: String? = null
)