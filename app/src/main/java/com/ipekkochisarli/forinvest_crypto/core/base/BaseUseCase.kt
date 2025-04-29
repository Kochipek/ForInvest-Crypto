package com.ipekkochisarli.forinvest_crypto.core.base

import com.ipekkochisarli.forinvest_crypto.core.data.remote.ApiResult
import com.ipekkochisarli.forinvest_crypto.core.domain.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

open class BaseUseCase<T> @Inject constructor() {

    operator fun invoke(action: suspend () -> Flow<ApiResult<T>>): Flow<UiState<T>> = flow {
        emit(UiState.Loading)
        try {
            action().collect { result ->
                when (result) {
                    is ApiResult.Success -> emit(UiState.Success(result.data))
                    is ApiResult.Error -> emit(UiState.Error(result.message))
                }
            }
        } catch (e: Exception) {
            emit(UiState.Error(e.message.orEmpty()))
        }
    }
}
