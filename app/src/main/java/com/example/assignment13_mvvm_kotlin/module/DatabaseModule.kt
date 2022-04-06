package com.example.assignment13_mvvm_kotlin.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.assignment13_mvvm_kotlin.db.HitsDatabase
import com.example.assignment13_mvvm_kotlin.db.HitsItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context: Context)  : HitsDatabase{
        return Room.databaseBuilder(context,HitsDatabase::class.java,"HitsDatabase").build()
    }

    @Provides
    @Singleton
    fun getHitsItemDao(hitsDatabase: HitsDatabase):HitsItemDao{
        return hitsDatabase.getHitsItemDao()
    }

}