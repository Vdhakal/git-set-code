package com.example.git_set_code.trip_database.Table;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 *
 */
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

    /**
     * @param trailerId
     * @param trailerCode
     * @param trailerDesc
     * @param truckId
     */
    public Trailer(int trailerId, String trailerCode, String trailerDesc, int truckId) {
        this.trailerId = trailerId;
        this.trailerCode = trailerCode;
        this.trailerDesc = trailerDesc;
        this.truckId = truckId;
    }

    /**
     * @return truckId
     */
    public int getTruckId() {
        return truckId;
    }

    /**
     * @param truckId
     */
    public void setTruckId(int truckId) {
        this.truckId = truckId;
    }

    /**
     * @return trailerId
     */
    public int getTrailerId() {
        return trailerId;
    }

    /**
     * @param trailerId
     */
    public void setTrailerId(int trailerId) {
        this.trailerId = trailerId;
    }

    /**
     * @return trailerCode
     */
    public String getTrailerCode() {
        return trailerCode;
    }

    /**
     * @param trailerCode
     */
    public void setTrailerCode(String trailerCode) {
        this.trailerCode = trailerCode;
    }

    /**
     * @return trailerDesc
     */
    public String getTrailerDesc() {
        return trailerDesc;
    }

    /**
     * @param trailerDesc
     */
    public void setTrailerDesc(String trailerDesc) {
        this.trailerDesc = trailerDesc;
    }
}
