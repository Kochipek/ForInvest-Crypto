package com.ipekkochisarli.forinvest_crypto.features.home.data.remote

import retrofit2.http.Query
import com.ipekkochisarli.forinvest_crypto.features.home.data.remote.dto.CoinsResponse
import com.ipekkochisarli.forinvest_crypto.features.home.data.remote.dto.TrendingCoinResponse
import retrofit2.http.GET

interface HomePageApi {

    @GET(END_POINT_GET_COIN_LIST)
    suspend fun getCoinList(@Query("vs_currency") currency: String = "usd"): CoinsResponse

    @GET(END_POINT_GET_TRENDING_COINS)
    suspend fun getTrendingCoins(): TrendingCoinResponse

    companion object {
        const val END_POINT_GET_COIN_LIST = "api/v3/coins/markets?"
        const val END_POINT_GET_TRENDING_COINS = "api/v3/search/trending"
    }
}