package com.ipekkochisarli.forinvest_crypto.features.home.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RoiDto(

    @SerializedName("times")
    val times: Double? = null,

    @SerializedName("percentage")
    val percentage: Double? = null,

    @SerializedName("currency")
    val currency: String? = null
)