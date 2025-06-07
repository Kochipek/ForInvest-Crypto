package com.ipekkochisarli.forinvest_crypto.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCoins(vararg coins: CoinEntity)

    @Query("SELECT * FROM coins WHERE name LIKE :query OR symbol LIKE :query")
    fun searchCoins(query: String): Flow<List<CoinEntity>>
}