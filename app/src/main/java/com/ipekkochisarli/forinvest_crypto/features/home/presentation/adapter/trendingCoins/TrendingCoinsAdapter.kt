package com.ipekkochisarli.forinvest_crypto.features.home.presentation.adapter.trendingCoins

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ipekkochisarli.forinvest_crypto.R
import com.ipekkochisarli.forinvest_crypto.core.base.BaseListAdapter
import com.ipekkochisarli.forinvest_crypto.features.home.domain.uimodel.TrendingCoinUiModel
import javax.inject.Inject

class TrendingCoinsAdapter @Inject constructor(): BaseListAdapter<TrendingCoinUiModel>(
    itemsSame = { oldItem, newItem -> oldItem == newItem },
    contentsSame = { oldItem, newItem -> oldItem == newItem }) {

    private var fullList: List<TrendingCoinUiModel> = emptyList()

    override fun submitList(list: List<TrendingCoinUiModel>?) {
        fullList = list?.take(5) ?: emptyList()
        super.submitList(fullList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, inflater: LayoutInflater, viewType: Int): TrendingCoinsViewHolder {
        val view = inflater.inflate(R.layout.item_trending_coins, parent, false)
        return TrendingCoinsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TrendingCoinsViewHolder).bind(getItem(position))
    }
}