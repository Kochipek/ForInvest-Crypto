package com.ipekkochisarli.forinvest_crypto.util.extensions

import androidx.core.text.HtmlCompat

fun String.addPrefix(value: String) = this + value

fun String.addSuffix(value: String) = value + this

fun Double.format(digits: Int) = "%.${digits}f".format(this)

fun String?.parseHtml(): String {
    if (this == null) return ""
    return HtmlCompat.fromHtml(
        this,
        HtmlCompat.FROM_HTML_MODE_COMPACT
    ).toString()
}