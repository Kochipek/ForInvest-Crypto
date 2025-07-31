package com.ipekkochisarli.forinvest_crypto.features.coinDetail.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.data.ChartPoint
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.domain.CoinChartTimeSpan

@Composable
fun ChartSection(
    pricePoints: List<ChartPoint>,
    selectedRange: CoinChartTimeSpan,
    onRangeChange: (CoinChartTimeSpan) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        TimeRangeSelector(
            selected = selectedRange,
            onTimeSelected = onRangeChange
        )

        Spacer(Modifier.height(12.dp))

        CoinLineChart(entries = pricePoints)
    }
}
