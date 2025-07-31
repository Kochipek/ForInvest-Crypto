package com.ipekkochisarli.forinvest_crypto.features.coinDetail.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.domain.CoinDetailUiModel

@Composable
fun CoinInfoSection(coin: CoinDetailUiModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        InfoText(label = "Market Cap Rank", value = coin.marketCapRank?.toString())
        InfoText(label = "Hashing Algorithm", value = coin.hashingAlgorithm)
        InfoText(label = "Block Time", value = coin.blockTimeInMinutes?.toString())
        InfoText(label = "Sentiment Up", value = coin.sentimentVotesUpPercentageFormatted?.toString())
        InfoText(label = "Sentiment Down", value = coin.sentimentVotesDownPercentageFormatted?.toString())
        InfoText(label = "Genesis Date", value = coin.genesisDate)

        coin.descriptionInEn?.takeIf { it.isNotBlank() }?.let { description ->
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.LightGray
            )
        }
    }
}

@Composable
fun InfoText(label: String, value: String?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Text(
            text = value ?: "N/A",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
    }
}