package com.ipekkochisarli.forinvest_crypto.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipekkochisarli.forinvest_crypto.core.domain.UiState
import com.ipekkochisarli.forinvest_crypto.features.home.domain.usecase.GetCoinListUseCase
import com.ipekkochisarli.forinvest_crypto.features.home.domain.usecase.GetTrendingCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coinListUseCase: GetCoinListUseCase,
    private val trendingCoinsUseCase: GetTrendingCoinsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomePageUiState())
    val state: StateFlow<HomePageUiState> = _state

    init {
        refreshCoinRequest()
    }

    private fun refreshCoinRequest() {
        viewModelScope.launch {
            while (isActive) {
                getCoinList()
                getTrendingCoins()
                delay(5000)
            }
        }
    }

    private suspend fun getCoinList() {
        coinListUseCase("usd").collect { result ->
            when (result) {
                is UiState.Success -> {
                    _state.value = _state.value.copy(
                        coinList = result.data,
                        isLoading = false
                    )
                }
                is UiState.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        message = result.message
                    )
                }
                is UiState.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
            }
        }
    }

    private suspend fun getTrendingCoins() {
        trendingCoinsUseCase().collect { result ->
            when (result) {
                is UiState.Success -> {
                    _state.value = _state.value.copy(
                        trendingCoinList = result.data,
                        isLoading = false
                    )
                }
                is UiState.Error -> {
                    _state.value = _state.value.copy(
                        message = result.message,
                        isLoading = false
                    )
                }
                is UiState.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
            }
        }
    }
}