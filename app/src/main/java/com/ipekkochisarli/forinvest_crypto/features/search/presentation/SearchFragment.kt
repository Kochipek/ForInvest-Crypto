package com.ipekkochisarli.forinvest_crypto.features.search.presentation

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ipekkochisarli.forinvest_crypto.core.base.BaseFragment
import com.ipekkochisarli.forinvest_crypto.databinding.FragmentSearchBinding
import com.ipekkochisarli.forinvest_crypto.features.home.presentation.CoinListUiState
import com.ipekkochisarli.forinvest_crypto.features.home.presentation.adapter.coinlist.CoinListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var coinListAdapter: CoinListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearchInput()
        collectState()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewResults.apply {
            coinListAdapter = CoinListAdapter()
            adapter = coinListAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun setupSearchInput() {
        binding.editTextSearch.doOnTextChanged { text, _, _, _ ->
            viewModel.searchCoins(text.toString())
        }
    }

    private fun collectState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                handleState(state)
            }
        }
    }

    private fun handleState(state: CoinListUiState) {
        if (state.isLoading) {
            // TODO: show loading spinner (e.g. progress bar)
        } else {
            coinListAdapter.submitList(state.coinList)
        }
    }
}
