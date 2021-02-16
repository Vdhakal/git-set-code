package com.example.git_set_code;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "saved_data")
public class SavedData {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double latitude;
    private double longtitude;
    private String userInput;

    public SavedData(double latitude, double longtitude, String userInput) {
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.userInput = userInput;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public String getUserInput() {
        return userInput;
    }
}
