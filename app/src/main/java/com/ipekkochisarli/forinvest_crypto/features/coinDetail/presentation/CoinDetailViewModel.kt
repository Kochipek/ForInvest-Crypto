package com.ipekkochisarli.forinvest_crypto.features.coinDetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipekkochisarli.forinvest_crypto.core.data.remote.ApiResult
import com.ipekkochisarli.forinvest_crypto.core.domain.UiState
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.domain.CoinChartTimeSpan
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.domain.CoinDetailUseCase
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.domain.MarketChartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val coinDetailUseCase: CoinDetailUseCase,
    private val marketChartUseCase: MarketChartUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CoinDetailUiState())
    val state: StateFlow<CoinDetailUiState> = _state

    private var currentCoinId: String? = null

    fun loadCoinDetail(coinId: String) {
        if (currentCoinId == coinId) return
        currentCoinId = coinId

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, errorMessage = null)

            coinDetailUseCase(coinId).collect { result ->
                when (result) {
                    is UiState.Success -> {
                        _state.value = _state.value.copy(
                            coinDetail = result.data,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                    is UiState.Error -> {
                        showError(result.message)
                    }

                    UiState.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                }
            }

            loadChartData(coinId, _state.value.timeRange)
        }
    }

    fun loadChartData(coinId: String, timeSpan: CoinChartTimeSpan) {
        _state.value = _state.value.copy(timeRange = timeSpan, errorMessage = null)

        viewModelScope.launch {
            marketChartUseCase(coinId, timeSpan.value).collect { result ->
                when (result) {
                    is ApiResult.Success -> {
                        if (result.data.isEmpty()) {
                            showError("Chart data is empty")
                        } else {
                            _state.value = _state.value.copy(pricePoints = result.data)
                        }
                    }
                    is ApiResult.Error -> {
                        showError(result.message)
                    }
                }
            }
        }
    }

    private fun showError(message: String) {
        _state.value = _state.value.copy(
            isLoading = false,
            errorMessage = message
        )
    }
}