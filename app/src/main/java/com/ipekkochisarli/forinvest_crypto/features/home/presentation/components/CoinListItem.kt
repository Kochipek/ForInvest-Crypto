package com.ipekkochisarli.forinvest_crypto.features.home.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.CoinUiModel

@Composable
fun CoinListItem(coin: CoinUiModel, onClick: (CoinUiModel) -> Unit) {
    val changeColor = if (coin.priceChangePercentage24h!! > 0) Color(0xFF50C878) else Color(0xFFFF5733)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(coin) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(coin.image)
                .crossfade(true)
                .transformations(CircleCropTransformation())
                .build(),
            contentDescription = coin.name,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            coin.name?.let { Text(text = it, style = MaterialTheme.typography.titleMedium) }
            coin.symbol?.let { Text(text = it, style = MaterialTheme.typography.bodySmall) }
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(text = "$${coin.currentPrice}", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "${coin.priceChangePercentage24h}%",
                color = changeColor,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
