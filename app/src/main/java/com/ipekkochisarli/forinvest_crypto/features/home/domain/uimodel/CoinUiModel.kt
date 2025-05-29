package com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel

data class CoinUiModel(
    val ath: Double? = null,
    val athChangePercentage: Double? = null,
    val athDate: String? = null,
    val atl: Double? = null,
    val atlChangePercentage: Double? = null,
    val atlDate: String? = null,
    val circulatingSupply: Double? = null,
    val currentPrice: String? = null,
    val fullyDilutedValuation: Long? = null,
    val high24h: Double? = null,
    val id: String? = null,
    val image: String? = null,
    val lastUpdated: String? = null,
    val low24h: Double? = null,
    val marketCap: String? = null,
    val marketCapChange24h: Double? = null,
    val marketCapChangePercentage24h: Double? = null,
    val marketCapRank: String? = null,
    val maxSupply: Double? = null,
    val name: String? = null,
    val priceChange24h: Double? = null,
    val priceChangePercentage24h: Double? = null,
    val priceChangeText: String? = null,
    val roi: RoiUiModel? = null,
    val symbol: String? = null,
    val totalSupply: Double? = null,
    val totalVolume: Long? = null
)

data class TrendingCoinUiModel(
    val id: String,
    val coinId: Int,
    val name: String,
    val symbol: String,
    val marketCapRank: String,
    val thumb: String,
    val small: String,
    val large: String,
    val slug: String,
    val score: Int,
    val priceBtc: Double
)