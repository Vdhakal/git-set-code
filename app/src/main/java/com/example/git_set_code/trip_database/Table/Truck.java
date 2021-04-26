package com.example.git_set_code.trip_database.Table;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Driver.class,
        parentColumns = "id",
        childColumns = "driver_id",
        onDelete = ForeignKey.NO_ACTION), tableName = "truck")
public class Truck {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "truck_id")
    private int truckId;
    private String truckCode;
    private String truckDesc;
    @ColumnInfo(name = "driver_id")
    private String driverId;

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverId() {
        return driverId;
    }

    public Truck(int truckId, String truckCode, String truckDesc, String driverId) {
        this.truckId = truckId;
        this.truckCode = truckCode;
        this.truckDesc = truckDesc;
        this.driverId = driverId;
    }

    public int getTruckId() {
        return truckId;
    }

    public String getTruckCode() {
        return truckCode;
    }

    public String getTruckDesc() {
        return truckDesc;
    }
    public void setTruckId(int truckId) {
        this.truckId = truckId;
    }

    public void setTruckCode(String truckCode) {
        this.truckCode = truckCode;
    }

    public void setTruckDesc(String truckDesc) {
        this.truckDesc = truckDesc;
    }
}
