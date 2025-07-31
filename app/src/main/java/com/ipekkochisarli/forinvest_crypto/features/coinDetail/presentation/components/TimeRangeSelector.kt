package com.ipekkochisarli.forinvest_crypto.features.coinDetail.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.domain.CoinChartTimeSpan

@Composable
fun TimeRangeSelector(
    selected: CoinChartTimeSpan,
    onTimeSelected: (CoinChartTimeSpan) -> Unit
) {
    val options = listOf(
        CoinChartTimeSpan.TIMESPAN_1DAYS to "1D",
        CoinChartTimeSpan.TIMESPAN_7DAYS to "7D",
        CoinChartTimeSpan.TIMESPAN_14DAYS to "14D",
        CoinChartTimeSpan.TIMESPAN_30DAYS to "30D",
        CoinChartTimeSpan.TIMESPAN_60DAYS to "60D"
    )

    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            options.forEach { (value, label) ->
                FilterChip(
                    selected = selected == value,
                    onClick = { onTimeSelected(value) },
                    label = { Text(label) }
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Selected range: ${options.find { it.first == selected }?.second ?: ""}",
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
