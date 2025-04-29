package com.ipekkochisarli.forinvest_crypto.features.home.presentation.adapter.coinlist

import android.view.View
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import com.ipekkochisarli.forinvest_crypto.databinding.ListItemCoinsBinding
import com.ipekkochisarli.forinvest_crypto.util.extensions.addPrefix
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.CoinUiModel

class CoinListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ListItemCoinsBinding.bind(itemView)

    fun bind(coin: CoinUiModel) {
        binding.coinIcon.load(coin.image) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }

        binding.coinName.text = coin.name
        binding.coinSymbol.text = coin.symbol
        binding.coinPrice.text = coin.currentPrice.toString().addPrefix("$")
        binding.coinChange.text = coin.priceChangePercentage24h.toString()

        val changeColor = if (coin.priceChangePercentage24h!! > 0) {
            "#50C878".toColorInt()
        } else {
            "#FF5733".toColorInt()
        }

        binding.coinChange.setTextColor(changeColor)
    }
}