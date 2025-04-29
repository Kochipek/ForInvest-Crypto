package com.ipekkochisarli.forinvest_crypto.features.home.data.remote

import retrofit2.http.Query
import com.ipekkochisarli.forinvest_crypto.features.home.data.remote.dto.CoinsResponse
import retrofit2.http.GET

interface HomePageApi {

    @GET(END_POINT_GET_COIN_LIST)
    suspend fun getCoinList(@Query("vs_currency") currency: String = "usd"): CoinsResponse

    @GET(END_POINT_GET_TRENDING_COINS)
    suspend fun getTrendingCoins(): CoinsResponse

    companion object {
        const val END_POINT_GET_COIN_LIST = "api/v3/coins/markets?vs_currency=usd"
        const val END_POINT_GET_TRENDING_COINS = "api/v3/search/trending"
    }
}