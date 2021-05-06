package com.example.git_set_code.trip_database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.git_set_code.trip_database.Table.Driver;
import com.example.git_set_code.trip_database.Table.SiteForm;
import com.example.git_set_code.trip_database.Table.SiteInformation;
import com.example.git_set_code.trip_database.Table.SourceForm;
import com.example.git_set_code.trip_database.Table.SourceInformation;
import com.example.git_set_code.trip_database.Table.Trailer;
import com.example.git_set_code.trip_database.Table.Trip;
import com.example.git_set_code.trip_database.Table.TripClientData;
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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTripClientData(TripClientData... tripClientData);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSourceForm(SourceForm... sourceForms);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSiteForm(SiteForm... siteForms);

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

    @Query("UPDATE trip_client SET selected = 1")
    public void setSelection();

    @Query("SELECT * FROM driver")
    public LiveData<List<Driver>> getAllDrivers();
    @Query("SELECT * FROM site_information ORDER BY site_id ASC")
    public  LiveData<List<SiteInformation>> getAllSite();
    @Query("SELECT * FROM source_information ORDER BY source_id ASC")
    public  LiveData<List<SourceInformation>> getAllSource();
    @Query("SELECT * FROM trailer")
    public  LiveData<List<Trailer>> getAllTrailer();
    @Query("SELECT * FROM truck")
    public  LiveData<List<Truck>> getAllTruck();
    @Query("SELECT * FROM trip")
    public  LiveData<List<Trip>> getAllTrip();
    @Query("SELECT * FROM trip_client")
    public  LiveData<List<TripClientData>> getAllTripClientData();
    @Query("SELECT DISTINCT productDesc FROM site_information")
    public  LiveData<List<String>> getAllProductTypes();
    @Query("SELECT selected FROM trip_client")
    public  LiveData<List<Integer>> getSelected();
    @Query("SELECT latitude FROM source_information")
    public  LiveData<List<Double>> getSourceLatitude();
    @Query("SELECT longitude FROM source_information")
    public  LiveData<List<Double>> getSourceLongitude();
    @Query("SELECT latitude FROM site_information")
    public  LiveData<List<Double>> getSiteLatitude();
    @Query("SELECT longitude FROM site_information")
    public  LiveData<List<Double>> getSiteLongitude();
    @Query("SELECT * FROM sourceForm")
    public  LiveData<List<SourceForm>> getSourceForm();
    @Query("SELECT * FROM siteForm")
    public  LiveData<List<SiteForm>> getSiteForm();


}
