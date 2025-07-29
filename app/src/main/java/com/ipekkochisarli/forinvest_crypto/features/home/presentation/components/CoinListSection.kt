package com.ipekkochisarli.forinvest_crypto.features.home.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.CoinUiModel

@Composable
fun CoinListSection(
    coins: List<CoinUiModel>,
    onItemClick: (CoinUiModel) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(coins) { coin ->
            CoinListItem(coin, onItemClick)
        }
    }
}
