package com.ipekkochisarli.forinvest_crypto.core.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoiConverter {
    @TypeConverter
    fun fromRoiEntity(roi: RoiEntity?): String? = roi?.let { Gson().toJson(it) }

    @TypeConverter
    fun toRoiEntity(roiString: String?): RoiEntity? =
        roiString?.let {
            Gson().fromJson(it, object : TypeToken<RoiEntity>() {}.type)
        }
}