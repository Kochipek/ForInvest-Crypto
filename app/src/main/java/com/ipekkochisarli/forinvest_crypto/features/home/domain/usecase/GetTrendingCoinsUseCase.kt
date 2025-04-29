package com.ipekkochisarli.forinvest_crypto.features.home.domain.usecase

import com.ipekkochisarli.forinvest_crypto.core.base.BaseUseCase
import com.ipekkochisarli.forinvest_crypto.core.domain.UiState
import com.ipekkochisarli.forinvest_crypto.features.home.domain.HomeRepository
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.CoinUiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrendingCoinsUseCase @Inject constructor(
    private val coinRepository: HomeRepository
) : BaseUseCase<List<CoinUiModel>>() {

    operator fun invoke(): Flow<UiState<List<CoinUiModel>>> = invoke {
        coinRepository.getTrendingCoins()
    }
}
