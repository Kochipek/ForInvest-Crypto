package com.ipekkochisarli.forinvest_crypto.features.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipekkochisarli.forinvest_crypto.core.domain.UiState
import com.ipekkochisarli.forinvest_crypto.features.home.presentation.CoinListUiState
import com.ipekkochisarli.forinvest_crypto.features.search.domain.SearchCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCoinsUseCase: SearchCoinsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListUiState())
    val state: StateFlow<CoinListUiState> = _state

    private var searchJob: Job? = null

    fun searchCoins(query: String) {
        if (query.isBlank()) {
            _state.value = CoinListUiState()
            return
        }

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            searchCoinsUseCase(query).collect { result ->
                when (result) {
                    is UiState.Success -> {
                        val distinctList = result.data.distinctBy { it.id }
                        _state.value = _state.value.copy(
                            coinList = distinctList,
                            isLoading = false,
                            message = null
                        )
                    }

                    is UiState.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            message = result.message
                        )
                    }

                    is UiState.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                }
            }
        }
    }
}