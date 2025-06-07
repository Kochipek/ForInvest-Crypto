package com.ipekkochisarli.forinvest_crypto.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "roins")
data class RoiEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val currency: String?,
    val percentage: Double?,
    val times: Double?
)