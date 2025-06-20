package com.ipekkochisarli.forinvest_crypto.features.coinDetail.domain


import com.ipekkochisarli.forinvest_crypto.core.base.BaseUseCase
import com.ipekkochisarli.forinvest_crypto.core.domain.UiState
import com.ipekkochisarli.forinvest_crypto.features.home.domain.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinDetailUseCase @Inject constructor(
    private val coinRepository: HomeRepository
) : BaseUseCase<CoinDetailUiModel>() {

    operator fun invoke(coinId: String): Flow<UiState<CoinDetailUiModel>> = invoke {
        coinRepository.getCoinDetail(coinId)
    }
}
