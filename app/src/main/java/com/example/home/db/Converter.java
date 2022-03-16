package com.example.home.db;

import androidx.room.TypeConverter;

import com.example.home.model.HighlightResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converter {

    @TypeConverter
    public static String fromObject(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    @TypeConverter
    public static Object toObject(String object){
        return new Gson().fromJson(object,Object.class);
    }

    @TypeConverter
    public static String fromHighlightResult(HighlightResult result){
        return new Gson().toJson(result);
    }

    @TypeConverter
    public static HighlightResult toHighlightResult(String object){
        return new Gson().fromJson(object,HighlightResult.class);
    }

    @TypeConverter
    public static List<String> toList(String value) {
        Type listType = new TypeToken<List<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }
    @TypeConverter
    public static String fromList(List<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

}
