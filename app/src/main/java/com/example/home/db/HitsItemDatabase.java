package com.example.home.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.home.model.HitsItem;

@Database(entities = {HitsItem.class} , version = 1)
@TypeConverters({Converter.class})
abstract public class HitsItemDatabase extends RoomDatabase {

    public abstract HitsItemDao getHitsItemDao();

    public static HitsItemDatabase getDatabaseInstance(Context context){
        return Room.databaseBuilder(context,HitsItemDatabase.class,"HitsItemDatabase").allowMainThreadQueries().build();
    }

}
