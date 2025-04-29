package com.ipekkochisarli.forinvest_crypto.features.home.presentation.adapter.trendingCoins

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.CoinUiModel
import androidx.core.graphics.toColorInt
import com.ipekkochisarli.forinvest_crypto.databinding.ItemTrendingCoinsBinding
import com.ipekkochisarli.forinvest_crypto.util.extensions.addPrefix

class TrendingCoinsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemTrendingCoinsBinding.bind(itemView)

    fun bind(coin: CoinUiModel) {
        binding.coinIcon.load(coin.image) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }

        with(binding) {
            coinPair.text = coin.symbol?.uppercase()
            coinPrice.text = coin.currentPrice.toString().addPrefix("$")
            volumeLabel.text = coin.marketCap.toString()
            coinVolume.text = coin.totalVolume.toString()
            binding.coinChange.text = coin.priceChangePercentage24h.toString()

            val changeColor = if (coin.priceChangePercentage24h!! > 0) {
                "#50C878".toColorInt()
            } else {
                "#FF5733".toColorInt()
            }
            coinChange.setTextColor(changeColor)
        }
    }
}