package com.ipekkochisarli.forinvest_crypto.features.coinDetail.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.domain.CoinChartTimeSpan
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.presentation.CoinDetailUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinDetailContent(
    state: CoinDetailUiState,
    onBackClick: () -> Unit,
    onTimeSpanChange: (CoinChartTimeSpan) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.Black)
    ) {
        TopAppBar(
            title = { Text(state.coinDetail?.name ?: "", color = Color.White) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
        )

        state.coinDetail?.let { coin ->
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = coin.largeImage,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Column {
                        coin.name?.let { Text(it, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White) }
                        Text(coin.symbol?.uppercase() ?: "", color = Color.Gray)
                    }
                }

                Spacer(Modifier.height(16.dp))
                Text("Price: $${coin.currentPriceInUsd}", color = Color.White)

                Spacer(Modifier.height(16.dp))

                ChartSection(
                    pricePoints = state.pricePoints,
                    selectedRange = state.timeRange,
                    onRangeChange = onTimeSpanChange
                )

                Spacer(Modifier.height(16.dp))
                CoinInfoSection(coin)
            }
        }
    }
}
