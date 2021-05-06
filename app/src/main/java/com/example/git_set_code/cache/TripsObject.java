package com.example.git_set_code.cache;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.git_set_code.viewmodels.TripsData;

@Entity(tableName = "trips_table")

/**
 * The TripsObject class
 */
public class TripsObject {


    @PrimaryKey(autoGenerate = true)
    public long id;

    @TypeConverters({NestedDataTypeConverter.class})
    public TripsData tripsData;

    /**
     * Constructor for TripsObject class
     *
     * @param tripsData, TripsData object
     */
    public TripsObject(TripsData tripsData) {
        this.tripsData = tripsData;
    }

    /**
     * Getter for tripsData
     *
     * @return tripsData, a TripsData object
     */
    public TripsData getTripsData() {
        return tripsData;
    }

    /**
     * Setter for tripsData
     *
     * @param tripsData, a TripsData converter
     */
    public void setTripsData(TripsData tripsData) {
        this.tripsData = tripsData;
    }
}
