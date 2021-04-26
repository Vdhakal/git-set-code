package com.example.git_set_code.trip_database.Table;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "driver")
public class Driver {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String driverCode;
    private String dirverName;

    public Driver(String driverCode, String dirverName) {
        this.driverCode = driverCode;
        this.dirverName = dirverName;
    }

    public void setDriverCode(String driverCode) {
        this.driverCode = driverCode;
    }


    public void setDirverName(String dirverName) {
        this.dirverName = dirverName;
    }

    public String getDriverCode() {
        return driverCode;
    }

    public String getDirverName() {
        return dirverName;
    }
}
