package com.example.git_set_code.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.git_set_code.database.SavedData;

import java.util.List;

@Dao
public interface SavedDataDao {
    @Insert
    void insert(SavedData savedData);
    @Delete
    void delete(SavedData savedData);
    @Query("SELECT * FROM saved_data ORDER BY id DESC")
    LiveData<List<SavedData>> getAllData();
}
