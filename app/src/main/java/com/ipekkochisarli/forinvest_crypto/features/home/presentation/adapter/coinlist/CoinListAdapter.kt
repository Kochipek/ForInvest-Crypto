package com.ipekkochisarli.forinvest_crypto.features.home.presentation.adapter.coinlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ipekkochisarli.forinvest_crypto.R
import com.ipekkochisarli.forinvest_crypto.core.base.BaseListAdapter
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.CoinUiModel
import javax.inject.Inject

class CoinListAdapter @Inject constructor(): BaseListAdapter<CoinUiModel>(
    itemsSame = { oldItem, newItem -> oldItem.id == newItem.id },
    contentsSame = { oldItem, newItem ->
        oldItem.currentPrice == newItem.currentPrice &&
                oldItem.priceChangePercentage24h == newItem.priceChangePercentage24h
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, inflater: LayoutInflater, viewType: Int): CoinListViewHolder {
        val view = inflater.inflate(R.layout.list_item_coins, parent, false)
        return CoinListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CoinListViewHolder).bind(getItem(position))
    }
}