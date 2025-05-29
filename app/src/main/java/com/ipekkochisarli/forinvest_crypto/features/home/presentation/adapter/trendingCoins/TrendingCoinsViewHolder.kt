package com.ipekkochisarli.forinvest_crypto.features.home.presentation.adapter.trendingCoins

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import com.ipekkochisarli.forinvest_crypto.databinding.ItemTrendingCoinsBinding
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.TrendingCoinUiModel
import com.ipekkochisarli.forinvest_crypto.util.extensions.format

class TrendingCoinsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemTrendingCoinsBinding.bind(itemView)

    fun bind(coin: TrendingCoinUiModel) {
        binding.coinIcon.load(coin.small) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }

        with(binding) {
            coinPair.text = coin.symbol
            coinPrice.text = coin.priceBtc.format(15)
        }
    }
}