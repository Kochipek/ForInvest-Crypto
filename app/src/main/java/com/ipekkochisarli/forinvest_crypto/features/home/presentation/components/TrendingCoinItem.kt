package com.ipekkochisarli.forinvest_crypto.features.home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.TrendingCoinUiModel
import com.ipekkochisarli.forinvest_crypto.util.extensions.format

@Composable
fun TrendingCoinItem(coin: TrendingCoinUiModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(coin.small)
                .crossfade(true)
                .transformations(CircleCropTransformation())
                .build(),
            contentDescription = coin.name,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        Text(text = coin.symbol, style = MaterialTheme.typography.bodySmall)
        Text(text = coin.priceBtc.format(15), style = MaterialTheme.typography.bodySmall)
    }
}
