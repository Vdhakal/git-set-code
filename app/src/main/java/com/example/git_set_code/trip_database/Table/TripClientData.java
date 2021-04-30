package com.example.git_set_code.trip_database.Table;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Trip.class,
        parentColumns = "trip_id",
        childColumns = "trip_id_fk",
        onDelete = ForeignKey.NO_ACTION),  tableName = "trip_client")
public class TripClientData {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "trip_id_fk")
    private int tripId;
    private int selected;

    public TripClientData(int selected, int tripId) {
        this.selected = selected;
        this.tripId = tripId;
    }


    public int getSelected() {
        return selected;
    }

    public int getTripId() {
        return tripId;
    }
}
