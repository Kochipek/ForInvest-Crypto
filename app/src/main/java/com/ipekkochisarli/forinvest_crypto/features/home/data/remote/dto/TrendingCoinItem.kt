package com.ipekkochisarli.forinvest_crypto.features.home.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TrendingCoinItem(
    val id: String,
    @SerializedName("coin_id")
    val coinId: Int,
    val name: String,
    val symbol: String,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int,
    val thumb: String,
    val small: String,
    val large: String,
    val slug: String,
    @SerializedName("price_btc")
    val priceBtc: Double,
    val score: Int,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double
)