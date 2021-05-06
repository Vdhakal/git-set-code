package com.example.git_set_code.trip_database.ViewModel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.git_set_code.trip_database.Repository.TripRepository;
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
public class TripViewModel extends AndroidViewModel {
    private TripRepository tripRepository;
    private LiveData<List<Driver>> allDrivers;
    private LiveData<List<SiteInformation>> allSite;
    private LiveData<List<SourceInformation>> allSource;
    private LiveData<List<Trailer>> allTrailer;
    private LiveData<List<Truck>> allTruck;
    private LiveData<List<Trip>> allTrip;
    private LiveData<List<TripClientData>> allTripClientData;
    private LiveData<List<Integer>> getSelected;
    private LiveData<List<Integer>> insertTripClient;
    private LiveData<List<Double>> sourceLatitudes;
    private LiveData<List<Double>> sourceLongitudes;
    private LiveData<List<Double>> siteLatitudes;
    private LiveData<List<Double>> siteLongitudes;


    /**
     * @param application
     */
    public TripViewModel(@NonNull Application application) {
        super(application);
        tripRepository = new TripRepository(application);
        allDrivers = tripRepository.getGetAllDrivers();
        allSite = tripRepository.getGetAllSite();
        allSource = tripRepository.getGetAllSource();
        allTrailer = tripRepository.getGetAllTrailer();
        allTruck = tripRepository.getGetAllTruck();
        allTrip = tripRepository.getGetAllTrip();
        allTripClientData = tripRepository.getGetAllTripClient();
        getSelected = tripRepository.getGetSelected();
        sourceLatitudes = tripRepository.getSourceLatitudes();
        sourceLongitudes = tripRepository.getSourceLongitudes();
        siteLatitudes = tripRepository.getSiteLatitudes();
        siteLongitudes = tripRepository.getSiteLongitudes();
    }

    /**
     * @return getSelected
     */
    public LiveData<List<Integer>> getGetSelected() {
        return getSelected;
    }

    /**
     * @return allDrivers
     */
    public LiveData<List<Driver>> getAllDrivers() {
        return allDrivers;
    }

    /**
     * @return allSite
     */
    public LiveData<List<SiteInformation>> getAllSite() {
        return allSite;
    }

    /**
     * @return allSource
     */
    public LiveData<List<SourceInformation>> getAllSource() {
        return allSource;
    }

    /**
     * @return allTrailer
     */
    public LiveData<List<Trailer>> getAllTrailer() {
        return allTrailer;
    }

    /**
     * @return allTruck
     */
    public LiveData<List<Truck>> getAllTruck() {
        return allTruck;
    }

    /**
     * @return allTrip
     */
    public LiveData<List<Trip>> getAllTrip() {
        return allTrip;
    }

    /**
     * @return allTripClientData
     */
    public LiveData<List<TripClientData>> getAllTripClientData() {
        return allTripClientData;
    }

    /**
     * @return insertTripClient
     */
    public LiveData<List<Integer>> getInsertTripClient() {
        return insertTripClient;
    }

    /**
     * @return sourceLatitudes
     */
    public LiveData<List<Double>> getSourceLatitudes() {
        return sourceLatitudes;
    }

    /**
     * @return sourceLongitudes
     */
    public LiveData<List<Double>> getSourceLongitudes() {
        return sourceLongitudes;
    }

    /**
     * @return siteLatitudes
     */
    public LiveData<List<Double>> getSiteLatitudes() {
        return siteLatitudes;
    }

    /**
     * @return siteLongitudes
     */
    public LiveData<List<Double>> getSiteLongitudes() {
        return siteLongitudes;
    }

    /**
     *
     */
    public void extractData() {
        tripRepository.extractData();
    }

    /**
     * @param tripClientData
     */
    public void setInsertTripClient(TripClientData tripClientData) {
        tripRepository.insertTripClientData(tripClientData);
    }

    /**
     * @param allDrivers
     */
    public void insertDrivers(Driver allDrivers) {
        tripRepository.insertDriver(allDrivers);
    }

    /**
     * @param siteInformation
     */
    public void insertSite(SiteInformation siteInformation) {
        tripRepository.insertSiteInformation(siteInformation);
    }

    /**
     * @param sourceInformation
     */
    public void insertSource(SourceInformation sourceInformation) {
        tripRepository.insertSourceinformation(sourceInformation);
    }

    /**
     * @param trailer
     */
    public void insertTrailer(Trailer trailer) {
        tripRepository.insertTrailer(trailer);
    }

    /**
     * @param truck
     */
    public void insertTruck(Truck truck) {
        tripRepository.insertTruck(truck);
    }

    /**
     * @param trip
     */
    public void insertTrip(Trip trip) {
        tripRepository.insertTrip(trip);
    }

    /**
     *
     */
    public void setSelection() {
        tripRepository.setSelection();
    }
}
