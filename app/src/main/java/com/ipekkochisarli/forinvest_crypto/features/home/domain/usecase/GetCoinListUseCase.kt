package com.ipekkochisarli.forinvest_crypto.features.home.domain.usecase

import com.ipekkochisarli.forinvest_crypto.core.base.BaseUseCase
import com.ipekkochisarli.forinvest_crypto.core.domain.UiState
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.CoinUiModel
import com.ipekkochisarli.forinvest_crypto.features.home.domain.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinListUseCase @Inject constructor(
    private val coinRepository: HomeRepository
) : BaseUseCase<List<CoinUiModel>>() {

    operator fun invoke(currency: String): Flow<UiState<List<CoinUiModel>>> = invoke {
        coinRepository.getCoins(currency)
    }
}
