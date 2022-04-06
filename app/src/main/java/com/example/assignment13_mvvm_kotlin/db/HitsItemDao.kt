package com.example.assignment13_mvvm_kotlin.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignment13_mvvm_kotlin.model.HitsItem

@Dao
interface HitsItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHitsItem(hitsItem: HitsItem)

    @Query("select * from HitsItem")
    suspend fun getSavedHits() : List<HitsItem>

}