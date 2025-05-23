package com.ipekkochisarli.forinvest_crypto.core.domain

    sealed class UiState<out T> {
        data object Loading : UiState<Nothing>()
        data class Success<out T>(val data: T) : UiState<T>()
        data class Error(val message: String) : UiState<Nothing>()
    }