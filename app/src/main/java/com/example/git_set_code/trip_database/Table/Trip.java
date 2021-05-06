package com.example.git_set_code.trip_database.Table;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 *
 */
@Entity(foreignKeys = @ForeignKey(entity = Driver.class,
        parentColumns = "id",
        childColumns = "driver_id",
        onDelete = ForeignKey.NO_ACTION), tableName = "trip")
public class Trip {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "trip_id")
    private int tripId;
    private String tripName;
    private String tripDate;
    @ColumnInfo(name = "driver_id")
    private String driverId;

    /**
     * @param tripId
     * @param tripName
     * @param tripDate
     * @param driverId
     */
    public Trip(int tripId, String tripName, String tripDate, String driverId) {
        this.tripId = tripId;
        this.tripName = tripName;
        this.tripDate = tripDate;
        this.driverId = driverId;
    }


    /**
     * @return tripId
     */
    public int getTripId() {
        return tripId;
    }

    /**
     * @param tripId
     */
    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    /**
     * @return tripName
     */
    public String getTripName() {
        return tripName;
    }

    /**
     * @param tripName
     */
    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    /**
     * @return tripDate
     */
    public String getTripDate() {
        return tripDate;
    }

    /**
     * @param tripDate
     */
    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    /**
     * @param driverId
     */
    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    /**
     * @return driverId
     */
    public String getDriverId() {
        return driverId;
    }

}
