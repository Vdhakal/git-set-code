package com.example.git_set_code.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 *
 */
@Entity(tableName = "saved_data")
public class SavedData {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double latitude;
    private double longtitude;
    private String userInput;

    /**
     * @param latitude
     * @param longtitude
     * @param userInput
     */
    public SavedData(double latitude, double longtitude, String userInput) {
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.userInput = userInput;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @return
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @return
     */
    public double getLongtitude() {
        return longtitude;
    }

    /**
     * @return
     */
    public String getUserInput() {
        return userInput;
    }
}
