package com.ipekkochisarli.forinvest_crypto.common

import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.ipekkochisarli.forinvest_crypto.R

class CustomMarkerView(context: Context, layoutResource: Int) : MarkerView(context, layoutResource) {

    private val tvValue: TextView = findViewById(R.id.tv_marker_value)

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        tvValue.text = e?.y?.toString() ?: ""
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF(-(width / 2).toFloat(), -height.toFloat())
    }
}
