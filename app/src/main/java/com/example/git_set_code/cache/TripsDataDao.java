package com.example.git_set_code.cache;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface TripsDataDao {
    @Insert
    void insert(TripsDataRoom tripsDataRoom);
    @Update
    void update(TripsDataRoom tripsDataRoom);
    @Delete
    void delete(TripsDataRoom tripsDataRoom);
    @Query("DELETE FROM trips_table")
    void deleteAll();

    @Query("SELECT * FROM trips_table")
    LiveData<List<TripsDataRoom>> getAllTripInformation();
}
