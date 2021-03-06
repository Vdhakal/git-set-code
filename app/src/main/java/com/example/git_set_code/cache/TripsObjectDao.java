package com.example.git_set_code.cache;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TripsObjectDao {
    @Insert
    void insert(TripsObject... tripsObjects);
    @Update
    void update(TripsObject... tripsObjects);

    @Query("DELETE FROM trips_table")
    void delete();

    @Query("SELECT * FROM trips_table")
    LiveData<List<TripsObject>> getAllTripInformation();
}

