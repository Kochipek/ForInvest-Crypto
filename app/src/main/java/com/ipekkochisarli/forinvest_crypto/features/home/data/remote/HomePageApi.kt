package com.ipekkochisarli.forinvest_crypto.features.home.data.remote

import com.ipekkochisarli.forinvest_crypto.features.coinDetail.data.CoinDetailDto
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.data.MarketDataDto
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.domain.CoinDetailUiModel
import retrofit2.http.Query
import com.ipekkochisarli.forinvest_crypto.features.home.data.remote.dto.CoinsResponse
import com.ipekkochisarli.forinvest_crypto.features.home.data.remote.dto.TrendingCoinResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HomePageApi {

    @GET(END_POINT_GET_COIN_LIST)
    suspend fun getCoinList(@Query("vs_currency") currency: String = "usd"): CoinsResponse

    @GET(END_POINT_GET_TRENDING_COINS)
    suspend fun getTrendingCoins(): TrendingCoinResponse

    @GET(COIN_DETAIL)
    suspend fun getCoinDetail(@Path("id") id: String): Response<CoinDetailDto>

    @GET(MARKET_CHARTS)
    suspend fun getMarketCharts(
        @Path("id") id: String,
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("days") days: Int = 7,
        @Query("interval") interval: String = "daily"
    ): MarketDataDto

    companion object {
        const val COIN_DETAIL = "\"coins/{id}\""
        const val END_POINT_GET_COIN_LIST = "api/v3/coins/markets?"
        const val END_POINT_GET_TRENDING_COINS = "api/v3/search/trending"
        const val MARKET_CHARTS = "coins/{id}/market_chart"
    }
}