package com.example.git_set_code.trip_database.Table;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Driver.class,
        parentColumns = "id",
        childColumns = "driver_id",
        onDelete = ForeignKey.NO_ACTION),  tableName = "trip")
public class Trip {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "trip_id")
    private int tripId;
    private String tripName;
    private String tripDate;
    @ColumnInfo(name = "driver_id")
    private String driverId;

    public Trip(int tripId, String tripName, String tripDate,String driverId) {
        this.tripId = tripId;
        this.tripName = tripName;
        this.tripDate = tripDate;
        this.driverId = driverId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverId() {
        return driverId;
    }

}
