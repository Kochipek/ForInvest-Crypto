package com.ipekkochisarli.forinvest_crypto.features.home.data.remote

import com.ipekkochisarli.forinvest_crypto.core.data.remote.ApiResult
import com.ipekkochisarli.forinvest_crypto.features.home.domain.HomeRepository
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.CoinUiModel
import com.ipekkochisarli.forinvest_crypto.features.home.domain.mapper.toDomain
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.TrendingCoinUiModel
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.toEntity
import com.ipekkochisarli.forinvest_crypto.features.search.data.local.CoinLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.collections.map

class HomeRepositoryImpl @Inject constructor(
    private val homePageApi: HomePageApi,
    private val coinLocalDataSource: CoinLocalDataSource
) : HomeRepository {

    override suspend fun getCoins(currency: String): Flow<ApiResult<List<CoinUiModel>>> = flow {
        try {
            val response = homePageApi.getCoinList(currency)

            val uiDataList = response.map { it.toDomain() }
            emit(ApiResult.Success(uiDataList))
            val coinEntities = uiDataList.map { it.toEntity() }

            // save to local db
            coinLocalDataSource.insertCoins(coinEntities)

        } catch (e: Exception) {
            emit(ApiResult.Error("Failed to load data: ${e.message}"))
        }
    }

    override suspend fun getTrendingCoins(): Flow<ApiResult<List<TrendingCoinUiModel>>> = flow {
        try {
            val response = homePageApi.getTrendingCoins()
            emit(ApiResult.Success(response.coins.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(ApiResult.Error("Failed to load data: ${e.message}"))
        }
    }

    override fun searchCoins(query: String): Flow<ApiResult<List<CoinUiModel>>> {
        return coinLocalDataSource.searchCoins(query).map { result ->
            when (result) {
                is ApiResult.Success -> {
                    ApiResult.Success(result.data.map { it.toDomain() })
                }

                is ApiResult.Error -> {
                    ApiResult.Error(result.message)
                }
            }
        }
    }
}