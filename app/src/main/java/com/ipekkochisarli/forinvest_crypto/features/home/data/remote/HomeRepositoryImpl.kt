package com.ipekkochisarli.forinvest_crypto.features.home.data.remote

import com.ipekkochisarli.forinvest_crypto.core.data.remote.ApiResult
import com.ipekkochisarli.forinvest_crypto.features.home.domain.HomeRepository
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.CoinUiModel
import com.ipekkochisarli.forinvest_crypto.features.home.domain.mapper.toDomain
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.TrendingCoinUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homePageApi: HomePageApi
) : HomeRepository {

    override fun getCoins(currency: String): Flow<ApiResult<List<CoinUiModel>>> = flow {
        try {
            val response = homePageApi.getCoinList(currency)
            emit(ApiResult.Success(response.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(ApiResult.Error("Failed to load data: ${e.message}"))
        }
    }

    override fun getTrendingCoins(): Flow<ApiResult<List<TrendingCoinUiModel>>> = flow {
        try {
            val response = homePageApi.getTrendingCoins()
            emit(ApiResult.Success(response.coins.mapNotNull { it.toDomain() }))
        } catch (e: Exception) {
            emit(ApiResult.Error("Failed to load data: ${e.message}"))
        }
    }
}