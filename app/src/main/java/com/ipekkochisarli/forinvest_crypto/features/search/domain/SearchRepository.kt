package com.ipekkochisarli.forinvest_crypto.features.search.domain

import com.ipekkochisarli.forinvest_crypto.core.data.remote.ApiResult
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.CoinUiModel
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchCoins(query : String): Flow<ApiResult<List<CoinUiModel>>>
}