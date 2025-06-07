package com.ipekkochisarli.forinvest_crypto.features.search.domain

import com.ipekkochisarli.forinvest_crypto.core.base.BaseUseCase
import com.ipekkochisarli.forinvest_crypto.core.domain.UiState
import com.ipekkochisarli.forinvest_crypto.features.home.domain.HomeRepository
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.CoinUiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchCoinsUseCase @Inject constructor(
    private val coinRepository: HomeRepository
) : BaseUseCase<List<CoinUiModel>>() {

    operator fun invoke(query: String): Flow<UiState<List<CoinUiModel>>> = invoke {
        coinRepository.searchCoins(query)
    }
}