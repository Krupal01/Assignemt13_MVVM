package com.example.home.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.home.model.HitsItem;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface HitsItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addHitsItem(HitsItem hitsItem);

    @Query("select * from HitsItem")
    List<HitsItem> getData();
}
