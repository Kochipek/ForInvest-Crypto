package com.ipekkochisarli.forinvest_crypto.features.coinDetail.presentation.components

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.ipekkochisarli.forinvest_crypto.R
import com.ipekkochisarli.forinvest_crypto.common.CustomMarkerView
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.data.ChartPoint
import androidx.core.graphics.toColorInt

@Composable
fun CoinLineChart(entries: List<ChartPoint>) {
    AndroidView(
        factory = { context ->
            LineChart(context).apply {
                description.isEnabled = false
                axisRight.isEnabled = false
                axisLeft.textColor = Color.WHITE
                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    textColor = Color.WHITE
                    setDrawGridLines(false)
                }
                legend.isEnabled = false
                marker = CustomMarkerView(context, R.layout.marker_view_layout)
                setBackgroundColor(Color.TRANSPARENT)
            }
        },
        update = { chart ->
            val chartEntries = entries.mapIndexed { index, point ->
                Entry(index.toFloat(), point.price.toFloat())
            }

            val dataSet = LineDataSet(chartEntries, "Price").apply {
                color = "#408ADB".toColorInt()
                setDrawCircles(false)
                lineWidth = 2f
                mode = LineDataSet.Mode.CUBIC_BEZIER
                setDrawFilled(true)
                fillColor = "#408ADB".toColorInt()
                fillAlpha = 60
                setDrawValues(false)
            }

            chart.data = LineData(dataSet)
            chart.invalidate()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
    )
}
