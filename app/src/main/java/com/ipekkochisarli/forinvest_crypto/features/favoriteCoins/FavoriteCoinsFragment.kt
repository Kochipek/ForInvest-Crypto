package com.ipekkochisarli.forinvest_crypto.features.favoriteCoins

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ipekkochisarli.forinvest_crypto.R
import com.ipekkochisarli.forinvest_crypto.core.base.BaseFragment
import com.ipekkochisarli.forinvest_crypto.databinding.FragmentFavoriteCoinsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteCoinsFragment : BaseFragment<FragmentFavoriteCoinsBinding>(FragmentFavoriteCoinsBinding::inflate) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_coins, container, false)
    }
}