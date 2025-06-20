package com.ipekkochisarli.forinvest_crypto.features.coinDetail.presentation

import android.os.Bundle
import android.graphics.Color
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.ipekkochisarli.forinvest_crypto.R
import com.ipekkochisarli.forinvest_crypto.core.base.BaseFragment
import com.ipekkochisarli.forinvest_crypto.databinding.FragmentCoinDetailBinding
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.data.ChartPoint
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.domain.CoinChartTimeSpan
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import coil3.load
import com.ipekkochisarli.forinvest_crypto.common.CustomMarkerView
import com.ipekkochisarli.forinvest_crypto.util.extensions.gone
import com.ipekkochisarli.forinvest_crypto.util.extensions.visible

@AndroidEntryPoint
class CoinDetailFragment : BaseFragment<FragmentCoinDetailBinding>(
    FragmentCoinDetailBinding::inflate
) {
    private val viewModel: CoinDetailViewModel by viewModels()
    private lateinit var lineChart: LineChart

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val coinId = arguments?.getString("coinId") ?: return

        lineChart = binding.lineChart

        binding.radioGroup.check(binding.radioButton7.id)

        viewModel.loadCoinDetail(coinId)
        viewModel.loadChartData(coinId, CoinChartTimeSpan.TIMESPAN_7DAYS)

        observeViewModel()
        setupChart()
        setupRadioGroup(coinId)

        binding.topAppBar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest { state ->

                if (state.isLoading) {
                    binding.progressBar.visible()
                    binding.clRoot.gone()

                } else if (state.errorMessage != null && state.errorMessage.isNotEmpty()) {
                    binding.progressBar.gone()
                    binding.clRoot.gone()

                    showErrorDialog(state.errorMessage) {
                        findNavController().navigateUp()
                    }

                } else {
                    binding.progressBar.gone()
                    binding.clRoot.visible()
                }

                state.coinDetail?.let { coin ->
                    binding.apply {
                        tvCoinName.text = coin.name
                        tvCoinSymbol.text = coin.symbol?.uppercase()
                        tvCoinPrice.text = "$${coin.currentPriceInUsd}"
                        tvDescription.text = coin.descriptionInEn

                        tvMarketCapRank.text =
                            getString(R.string.market_cap_rank, coin.marketCapRank?.toString())
                        tvHashingAlgorithm.text =
                            getString(R.string.hashing_algorithm, coin.hashingAlgorithm)
                        tvBlockTime.text =
                            getString(R.string.block_time, coin.blockTimeInMinutes?.toString())
                        tvSentimentUp.text = getString(
                            R.string.sentiment_up,
                            coin.sentimentVotesUpPercentageFormatted ?: "N/A"
                        )
                        tvSentimentDown.text = getString(
                            R.string.sentiment_down,
                            coin.sentimentVotesDownPercentageFormatted ?: "N/A"
                        )
                        tvGenesisDate.text = getString(R.string.genesis_date, coin.genesisDate)
                    }
                    binding.coinIcon.load(coin.largeImage)
                }

                updateChart(state.pricePoints)
            }
        }
    }

    private fun setupRadioGroup(coinId: String) {
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedTimeSpan = when (checkedId) {
                binding.radioButton1.id -> CoinChartTimeSpan.TIMESPAN_1DAYS
                binding.radioButton7.id -> CoinChartTimeSpan.TIMESPAN_7DAYS
                binding.radioButton14.id -> CoinChartTimeSpan.TIMESPAN_14DAYS
                binding.radioButton30.id -> CoinChartTimeSpan.TIMESPAN_30DAYS
                binding.radioButton60.id -> CoinChartTimeSpan.TIMESPAN_60DAYS
                else -> CoinChartTimeSpan.TIMESPAN_7DAYS
            }
            viewModel.loadChartData(coinId, selectedTimeSpan)
        }
    }

    private fun setupChart() {
        lineChart.apply {
            marker = CustomMarkerView(context, R.layout.marker_view_layout)
            description.isEnabled = false
            setTouchEnabled(true)
            isDragEnabled = true
            setScaleEnabled(true)
            setPinchZoom(true)
            setDrawGridBackground(false)
            setBackgroundColor(Color.BLACK)

            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                textColor = Color.WHITE
                setDrawGridLines(true)
                granularity = 1f
                setAvoidFirstLastClipping(true)
                textSize = 12f
            }

            axisLeft.apply {
                textColor = Color.WHITE
                setDrawGridLines(true)
                textSize = 12f
                setDrawZeroLine(true)
            }
            axisRight.isEnabled = false

            legend.isEnabled = false

            animateX(500)
        }
    }

    private fun updateChart(pricePoints: List<ChartPoint>) {
        if (pricePoints.isEmpty()) {
            lineChart.clear()
            return
        }

        val entries = pricePoints.mapIndexed { index, point ->
            Entry(index.toFloat(), point.price.toFloat())
        }

        val lineDataSet = LineDataSet(entries, "Price").apply {
            color = resources.getColor(R.color.primary_blue, null)
            setDrawCircles(false)
            lineWidth = 2.5f
            mode = LineDataSet.Mode.CUBIC_BEZIER
            setDrawFilled(true)
            fillColor = resources.getColor(R.color.primary_blue, null)
            fillAlpha = 80
            setDrawValues(false)
        }

        lineChart.data = LineData(lineDataSet)
        lineChart.invalidate()
    }
}