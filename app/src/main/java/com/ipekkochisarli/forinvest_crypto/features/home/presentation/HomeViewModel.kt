package com.ipekkochisarli.forinvest_crypto.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipekkochisarli.forinvest_crypto.core.domain.UiState
import com.ipekkochisarli.forinvest_crypto.features.home.domain.usecase.GetCoinListUseCase
import com.ipekkochisarli.forinvest_crypto.features.home.domain.usecase.GetTrendingCoinsUseCase
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.CoinUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coinListUseCase: GetCoinListUseCase,
    private val trendingCoinsUseCase: GetTrendingCoinsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomePageUiState())
    val state: StateFlow<HomePageUiState> = _state

    fun getCoinList() {
        fetchCoinList { coinListUseCase("usd") }
    }

    fun getTrendingCoins() {
        fetchCoinList { trendingCoinsUseCase() }
    }

    private fun fetchCoinList(useCase: suspend () -> Flow<UiState<List<CoinUiModel>>>) {
        viewModelScope.launch {
            useCase().collect { result ->
                _state.value = when (result) {
                    is UiState.Error -> _state.value.copy(message = result.message, isLoading = false)
                    is UiState.Success -> _state.value.copy(coinList = result.data, isLoading = false)
                    UiState.Loading -> _state.value.copy(isLoading = true)
                }
            }
        }
    }
}
