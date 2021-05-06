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
public class StringTypeConverter {
    private static Gson gson = new Gson();

    /**
     * @param data
     * @return List<String>
     */
    @TypeConverter
    public static List<String> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<String>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    /**
     * @param someObjects
     * @return a String
     */
    @TypeConverter
    public static String someObjectListToString(List<String> someObjects) {
        return gson.toJson(someObjects);
    }
}
