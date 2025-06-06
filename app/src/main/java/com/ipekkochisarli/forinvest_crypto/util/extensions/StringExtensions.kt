package com.ipekkochisarli.forinvest_crypto.util.extensions

fun String.addPrefix(value: String) = this + value

fun String.addSuffix(value: String) = value + this

fun Double.format(digits: Int) = "%.${digits}f".format(this)