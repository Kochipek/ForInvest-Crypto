package com.ipekkochisarli.forinvest_crypto.features.coinDetail.data

import com.google.gson.annotations.SerializedName
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.domain.CoinDetailUiModel
import com.ipekkochisarli.forinvest_crypto.util.extensions.parseHtml

data class CoinDetailDto(
    @SerializedName("block_time_in_minutes")
    val blockTimeInMinutes: Int?,
    val categories: List<String>?,
    @SerializedName("country_origin")
    val countryOrigin: String?,
    val description: DescriptionDto?,
    @SerializedName("genesis_date")
    val genesisDate: String?,
    @SerializedName("hashing_algorithm")
    val hashingAlgorithm: String?,
    val id: String?,
    val image: CoinImageDto?,
    @SerializedName("last_updated")
    val lastUpdated: String?,
    val links: LinksDto?,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int?,
    @SerializedName("market_data")
    val marketData: MarketDataDto?,
    val name: String?,
    @SerializedName("preview_listing")
    val previewListing: Boolean?,
    @SerializedName("sentiment_votes_down_percentage")
    val sentimentVotesDownPercentage: Double?,
    @SerializedName("sentiment_votes_up_percentage")
    val sentimentVotesUpPercentage: Double?,
    val symbol: String?,
    @SerializedName("watchlist_portfolio_users")
    val watchlistPortfolioUsers: Int?,
    @SerializedName("web_slug")
    val webSlug: String?
)

data class DescriptionDto(
    val en: String?
)

data class CoinImageDto(
    val large: String?,
    val small: String?,
    val thumb: String?
)

data class LinksDto(
    val homepage: List<String>?
)

data class MarketDataDto(
    @SerializedName("current_price")
    val currentPrice: CurrentPriceDto?,
)

data class CurrentPriceDto(
    val usd: Double?
)

data class ChartPoint(
    val timestamp: Long,
    val price: Double
)

data class CoinMarketChart(
    val prices: List<List<Double>>,
    @SerializedName("market_caps")
    val marketCaps: List<List<Double>>,
    @SerializedName("total_volumes")
    val totalVolumes: List<List<Double>>
)

fun CoinMarketChart.toDomain(): List<ChartPoint> {
    return prices.map { priceEntry ->
        ChartPoint(
            timestamp = priceEntry[0].toLong(),
            price = priceEntry[1]
        )
    }
}

fun CoinDetailDto.toDomain(): CoinDetailUiModel = CoinDetailUiModel(
    blockTimeInMinutes = blockTimeInMinutes,
    categories = categories,
    countryOrigin = countryOrigin,
    descriptionInEn = description?.en?.parseHtml(),
    genesisDate = genesisDate,
    hashingAlgorithm = hashingAlgorithm,
    id = id,
    largeImage = image?.large,
    lastUpdated = lastUpdated,
    firsHomePageLink = links?.homepage?.first(),
    marketCapRank = marketCapRank,
    currentPriceInUsd = marketData?.currentPrice?.usd,
    name = name,
    previewListing = previewListing,
    sentimentVotesDownPercentage = sentimentVotesDownPercentage ?: 0.0,
    sentimentVotesUpPercentage = (sentimentVotesUpPercentage ?: 0.0).toFloat(),
    symbol = symbol,
    watchlistPortfolioUsers = watchlistPortfolioUsers,
    webSlug = webSlug,
    sentimentVotesDownPercentageFormatted = sentimentVotesDownPercentage ?: 0.0,
    sentimentVotesUpPercentageFormatted = sentimentVotesUpPercentage ?: 0.0,
    currentPriceFormatted = "%.2f".format(marketData?.currentPrice?.usd ?: 0.0),
    marketCapRankFormatted = "#${marketCapRank ?: "N/A"}"
)