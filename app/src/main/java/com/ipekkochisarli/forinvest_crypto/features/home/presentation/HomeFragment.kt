package com.ipekkochisarli.forinvest_crypto.features.home.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ipekkochisarli.forinvest_crypto.core.base.BaseFragment
import com.ipekkochisarli.forinvest_crypto.databinding.FragmentHomeBinding
import com.ipekkochisarli.forinvest_crypto.features.home.presentation.adapter.coinlist.CoinListAdapter
import com.ipekkochisarli.forinvest_crypto.features.home.presentation.adapter.trendingCoins.TrendingCoinsAdapter
import com.ipekkochisarli.forinvest_crypto.util.extensions.gone
import com.ipekkochisarli.forinvest_crypto.util.extensions.visible
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

        coinListAdapter.onItemClick = { coin ->
            val coinId = coin.id
            if (coinId.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Invalid coin ID", Toast.LENGTH_SHORT).show()
            } else {
                val action = HomeFragmentDirections.actionHomeFragmentToCoinDetailFragment(coinId)
                findNavController().navigate(action)
            }
        }

        binding.rvFeaturedCoins.apply {
            trendingCoinListAdapter = TrendingCoinsAdapter()
            adapter = trendingCoinListAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun handleState(state: CoinListUiState) {
        if (state.isLoading) {
            binding.progressBar.visible()
        } else {
            binding.progressBar.gone()
            coinListAdapter.submitList(state.coinList)
            trendingCoinListAdapter.submitList(state.trendingCoinList)
        }

        if (!state.message.isNullOrEmpty()) {
            showErrorDialog(state.message)
        }
    }
}