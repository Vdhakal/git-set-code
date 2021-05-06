package com.example.git_set_code.trip_database.Table;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * This class consists of the driver information
 */
@Entity(tableName = "driver")
public class Driver {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String driverCode;
    private String driverName;

    /**
     * Constructor for Driver
     *
     * @param driverCode
     * @param driverName
     */
    public Driver(String driverCode, String driverName) {
        this.driverCode = driverCode;
        this.driverName = driverName;
    }

    /**
     * Setter for driverCode
     *
     * @param driverCode
     */
    public void setDriverCode(String driverCode) {
        this.driverCode = driverCode;
    }

    /**
     * Setter for driverName
     *
     * @param driverName
     */
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    /**
     * Getter for driverCode
     *
     * @return driverCode
     */
    public String getDriverCode() {
        return driverCode;
    }

    /**
     * Getter for
     *
     * @return driverName
     */
    public String getDriverName() {
        return driverName;
    }
}
