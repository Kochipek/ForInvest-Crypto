package com.ipekkochisarli.forinvest_crypto.features.search.data.local

import com.ipekkochisarli.forinvest_crypto.core.data.remote.ApiResult
import com.ipekkochisarli.forinvest_crypto.features.home.domain.mapper.toDomain
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.CoinUiModel
import com.ipekkochisarli.forinvest_crypto.features.search.domain.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchRepositoryImpl(
    private val localDataSource: CoinLocalDataSource
): SearchRepository {
    override fun searchCoins(query: String): Flow<ApiResult<List<CoinUiModel>>> {
        return localDataSource.searchCoins(query).map { result ->
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