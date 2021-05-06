package com.example.git_set_code.typeConverters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import androidx.room.TypeConverter;

import java.util.Collections;
import java.util.List;

/**
 *
 */
public class FloatTypeConverters {
    private static Gson gson = new Gson();

    /**
     * @param data
     * @return
     */
    @TypeConverter
    public static List<Double> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Double>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    /**
     * @param someObjects
     * @return a String
     */
    @TypeConverter
    public static String someObjectListToString(List<Double> someObjects) {
        return gson.toJson(someObjects);
    }
}
