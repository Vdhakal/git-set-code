package com.example.git_set_code.trip_database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.git_set_code.trip_database.Table.Driver;
import com.example.git_set_code.trip_database.Table.SiteInformation;
import com.example.git_set_code.trip_database.Table.SourceInformation;
import com.example.git_set_code.trip_database.Table.Trailer;
import com.example.git_set_code.trip_database.Table.Trip;
import com.example.git_set_code.trip_database.Table.Truck;

import java.util.List;

@Dao
public interface TripDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertDrivers(Driver... drivers);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTrip(Trip... trips);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTruck(Truck... trucks);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTrailer(Trailer... trailers);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSource(SourceInformation... sourceInformations);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSite(SiteInformation... siteInformations);

    @Delete
    public void deleteDrivers(Driver... drivers);
    @Delete
    public void deleteTrip(Trip... trips);
    @Delete
    public void deleteTruck(Truck... trucks);
    @Delete
    public void deleteTrailer(Trailer... trailers);
    @Delete
    public void deleteSource(SourceInformation... sourceInformation);
    @Delete
    public void deleteSite(SiteInformation... siteInformation);

    @Query("SELECT * FROM driver")
    public LiveData<List<Driver>> getAllDrivers();
    @Query("SELECT * FROM site_information")
    public  LiveData<List<SiteInformation>> getAllSite();
    @Query("SELECT * FROM source_information")
    public  LiveData<List<SourceInformation>> getAllSource();
    @Query("SELECT * FROM trailer")
    public  LiveData<List<Trailer>> getAllTrailer();
    @Query("SELECT * FROM truck")
    public  LiveData<List<Truck>> getAllTruck();
    @Query("SELECT * FROM trip")
    public  LiveData<List<Trip>> getAllTrip();
    @Query("SELECT DISTINCT productDesc FROM site_information")
    public  LiveData<List<String>> getAllProductTypes();
}
