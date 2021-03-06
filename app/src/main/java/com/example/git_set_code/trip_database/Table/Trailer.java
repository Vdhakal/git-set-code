package com.example.git_set_code.trip_database.Table;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Truck.class,
        parentColumns = "truck_id",
        childColumns = "fk_truck_id",
        onDelete = ForeignKey.NO_ACTION), tableName = "trailer")
public class Trailer {
    @PrimaryKey
    @NonNull
    private int trailerId;
    private String trailerCode;
    private String trailerDesc;
    @ColumnInfo(name = "fk_truck_id")
    private int truckId;


    public Trailer( int trailerId, String trailerCode, String trailerDesc, int truckId) {
        this.trailerId = trailerId;
        this.trailerCode = trailerCode;
        this.trailerDesc = trailerDesc;
        this.truckId = truckId;
    }

    public int getTruckId() {
        return truckId;
    }

    public void setTruckId(int truckId) {
        this.truckId = truckId;
    }

    public int getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(int trailerId) {
        this.trailerId = trailerId;
    }

    public String getTrailerCode() {
        return trailerCode;
    }

    public void setTrailerCode(String trailerCode) {
        this.trailerCode = trailerCode;
    }

    public String getTrailerDesc() {
        return trailerDesc;
    }

    public void setTrailerDesc(String trailerDesc) {
        this.trailerDesc = trailerDesc;
    }
}
