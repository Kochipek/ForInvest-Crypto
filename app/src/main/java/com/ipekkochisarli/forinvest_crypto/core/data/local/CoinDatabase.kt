package com.ipekkochisarli.forinvest_crypto.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        CoinEntity::class,
        RoiEntity::class
    ],
    version = 1,
    exportSchema = true
)

@TypeConverters(RoiConverter::class)
abstract class CoinDatabase : RoomDatabase() {
    abstract fun coinDao(): CoinDao
}