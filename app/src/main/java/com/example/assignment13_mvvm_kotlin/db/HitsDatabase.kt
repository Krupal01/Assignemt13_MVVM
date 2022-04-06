package com.example.assignment13_mvvm_kotlin.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.assignment13_mvvm_kotlin.converter.Converter
import com.example.assignment13_mvvm_kotlin.model.HitsItem

@Database(entities = [HitsItem::class], version = 1)
@TypeConverters(Converter::class)
abstract class HitsDatabase : RoomDatabase(){

    abstract fun getHitsItemDao() : HitsItemDao
}