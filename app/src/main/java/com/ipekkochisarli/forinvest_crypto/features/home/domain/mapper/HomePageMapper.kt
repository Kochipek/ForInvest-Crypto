package com.ipekkochisarli.forinvest_crypto.features.home.domain.mapper

import com.ipekkochisarli.forinvest_crypto.features.home.data.remote.dto.CoinDto
import com.ipekkochisarli.forinvest_crypto.features.home.data.remote.dto.RoiDto
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.CoinUiModel
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.RoiUiModel

fun CoinDto.toDomain(): CoinUiModel =
    CoinUiModel(
        id = id.orEmpty(),
        name = name.orEmpty(),
        symbol = symbol.orEmpty(),
        image = image.orEmpty(),
        currentPrice = currentPrice?.let { "%.2f".format(it) } ?: "N/A",
        marketCap = marketCap?.let { "%.2f".format(it.toDouble() / 1_000_000_000) } ?: "N/A",
        marketCapRank = marketCapRank?.let { "#$it" } ?: "N/A",
        totalVolume = totalVolume ?: 0L,
        high24h = high24h ?: 0.0,
        low24h = low24h ?: 0.0,
        priceChange24h = priceChange24h ?: 0.0,
        priceChangePercentage24h = priceChangePercentage24h ?: 0.0,
        marketCapChange24h = marketCapChange24h ?: 0.0,
        marketCapChangePercentage24h = marketCapChangePercentage24h ?: 0.0,
        circulatingSupply = circulatingSupply ?: 0.0,
        totalSupply = totalSupply,
        maxSupply = maxSupply,
        ath = ath ?: 0.0,
        athChangePercentage = athChangePercentage ?: 0.0,
        athDate = athDate.orEmpty(),
        atl = atl ?: 0.0,
        atlChangePercentage = atlChangePercentage ?: 0.0,
        atlDate = atlDate.orEmpty(),
        lastUpdated = lastUpdated.orEmpty(),
        roi = roi?.toDomain(),
        fullyDilutedValuation = fullyDilutedValuation,
        priceChangeText = priceChangePercentage24h?.let {
            if (it >= 0) {
                "+${"%.2f".format(it)}%"
            } else {
                "${"%.2f".format(it)}%"
            }
        } ?: "N/A"
    )


fun RoiDto.toDomain(): RoiUiModel =
    RoiUiModel(
        currency = currency.orEmpty(),
        percentage = (percentage as? Number)?.toDouble() ?: 0.0,
        times = (times as? Number)?.toDouble() ?: 0.0
    )