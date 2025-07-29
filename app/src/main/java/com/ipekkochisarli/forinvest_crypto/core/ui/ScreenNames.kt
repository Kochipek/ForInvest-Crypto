package com.ipekkochisarli.forinvest_crypto.core.ui

sealed class ScreenNames(val route: String) {
    object Home : ScreenNames("home")
    object CoinDetail : ScreenNames("coinDetail/{coinId}") {
        fun createRoute(coinId: String) = "coinDetail/$coinId"
    }
}