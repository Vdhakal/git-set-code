package com.example.git_set_code.cache;

import androidx.room.TypeConverter;

import com.example.git_set_code.viewmodels.TripsData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 *
 */
public class NestedDataTypeConverter {
    private static Gson gson = new Gson();
    private static Type type = new TypeToken<TripsData>() {
    }.getType();

    /**
     * @param json
     * @return
     */
    @TypeConverter
    public static TripsData stringToNestedData(String json) {
        return gson.fromJson(json, type);
    }

    /**
     * @param nestedData
     * @return
     */
    @TypeConverter
    public static String nestedDataToString(TripsData nestedData) {
        return gson.toJson(nestedData, type);
    }
}
