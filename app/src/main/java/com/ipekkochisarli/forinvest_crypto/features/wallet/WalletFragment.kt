package com.ipekkochisarli.forinvest_crypto.features.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ipekkochisarli.forinvest_crypto.R
import com.ipekkochisarli.forinvest_crypto.core.base.BaseFragment
import com.ipekkochisarli.forinvest_crypto.databinding.FragmentWalletBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalletFragment : BaseFragment<FragmentWalletBinding>(FragmentWalletBinding::inflate) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wallet, container, false)
    }
}