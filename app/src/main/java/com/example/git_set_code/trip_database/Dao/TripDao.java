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
import com.example.git_set_code.trip_database.Table.TripClientData;
import com.example.git_set_code.trip_database.Table.Truck;

import java.util.List;

/**
 *
 */
@Dao
public interface TripDao {
    /**
     * @param drivers
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertDrivers(Driver... drivers);

    /**
     * @param trips
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTrip(Trip... trips);

    /**
     * @param trucks
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTruck(Truck... trucks);

    /**
     * @param trailers
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTrailer(Trailer... trailers);

    /**
     * @param sourceInformations
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSource(SourceInformation... sourceInformations);

    /**
     * @param siteInformations
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSite(SiteInformation... siteInformations);

    /**
     * @param tripClientData
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTripClientData(TripClientData... tripClientData);

    /**
     * @param drivers
     */
    @Delete
    public void deleteDrivers(Driver... drivers);

    /**
     * @param trips
     */
    @Delete
    public void deleteTrip(Trip... trips);

    /**
     * @param trucks
     */
    @Delete
    public void deleteTruck(Truck... trucks);

    /**
     * @param trailers
     */
    @Delete
    public void deleteTrailer(Trailer... trailers);

    /**
     * @param sourceInformation
     */
    @Delete
    public void deleteSource(SourceInformation... sourceInformation);

    /**
     * @param siteInformation
     */
    @Delete
    public void deleteSite(SiteInformation... siteInformation);

    /**
     *
     */
    @Query("UPDATE trip_client SET selected = 1")
    public void setSelection();

    /**
     * @return
     */
    @Query("SELECT * FROM driver")
    public LiveData<List<Driver>> getAllDrivers();

    /**
     * @return
     */
    @Query("SELECT * FROM site_information ORDER BY site_id ASC")
    public LiveData<List<SiteInformation>> getAllSite();

    /**
     * @return
     */
    @Query("SELECT * FROM source_information ORDER BY source_id ASC")
    public LiveData<List<SourceInformation>> getAllSource();

    /**
     * @return
     */
    @Query("SELECT * FROM trailer")
    public LiveData<List<Trailer>> getAllTrailer();

    /**
     * @return
     */
    @Query("SELECT * FROM truck")
    public LiveData<List<Truck>> getAllTruck();

    /**
     * @return
     */
    @Query("SELECT * FROM trip")
    public LiveData<List<Trip>> getAllTrip();

    /**
     * @return
     */
    @Query("SELECT * FROM trip_client")
    public LiveData<List<TripClientData>> getAllTripClientData();

    /**
     * @return
     */
    @Query("SELECT DISTINCT productDesc FROM site_information")
    public LiveData<List<String>> getAllProductTypes();

    /**
     * @return
     */
    @Query("SELECT selected FROM trip_client")
    public LiveData<List<Integer>> getSelected();

    /**
     * @return
     */
    @Query("SELECT latitude FROM source_information")
    public LiveData<List<Double>> getSourceLatitude();

    /**
     * @return
     */
    @Query("SELECT longitude FROM source_information")
    public LiveData<List<Double>> getSourceLongitude();

    /**
     * @return
     */
    @Query("SELECT latitude FROM site_information")
    public LiveData<List<Double>> getSiteLatitude();

    /**
     * @return
     */
    @Query("SELECT longitude FROM site_information")
    public LiveData<List<Double>> getSiteLongitude();
}
