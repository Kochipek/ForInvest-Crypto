package com.ipekkochisarli.forinvest_crypto.features.home.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ipekkochisarli.forinvest_crypto.core.base.BaseFragment
import com.ipekkochisarli.forinvest_crypto.databinding.FragmentHomeBinding
import com.ipekkochisarli.forinvest_crypto.features.home.presentation.adapter.coinlist.CoinListAdapter
import com.ipekkochisarli.forinvest_crypto.features.home.presentation.adapter.trendingCoins.TrendingCoinsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var coinListAdapter : CoinListAdapter
    private lateinit var trendingCoinListAdapter : TrendingCoinsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViews()
        collectPageState()
        viewModel.getData()

    }

    private fun collectPageState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                handleState(state)
            }
        }
    }

    private fun setupRecyclerViews() {
        binding.rvCoinList.apply {
            coinListAdapter = CoinListAdapter()
            adapter = coinListAdapter
            setHasFixedSize(true)
        }

        binding.rvFeaturedCoins.apply {
            trendingCoinListAdapter = TrendingCoinsAdapter()
            adapter = trendingCoinListAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun handleState(state: HomePageUiState) {
        if (state.isLoading) {
            //!todo show loading spinner
        } else {
            coinListAdapter.submitList(state.coinList)
            trendingCoinListAdapter.submitList(state.trendingCoinList)
        }
    }
}