package com.example.git_set_code.cache;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.git_set_code.viewmodels.TripsData;

@Entity(tableName = "trips_table")

public class TripsObject {
    @PrimaryKey(autoGenerate = true)
    public long id;
    @TypeConverters({NestedDataTypeConverter.class})
    public TripsData tripsData;

    public TripsObject(TripsData tripsData) {
        this.tripsData = tripsData;
    }

    public TripsData getTripsData() {
        return tripsData;
    }

    public void setTripsData(TripsData tripsData) {
        this.tripsData = tripsData;
    }
}
