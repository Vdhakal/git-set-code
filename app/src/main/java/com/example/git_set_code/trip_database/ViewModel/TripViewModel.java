package com.example.git_set_code.trip_database.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.git_set_code.trip_database.Repository.TripRepository;
import com.example.git_set_code.trip_database.Table.Driver;
import com.example.git_set_code.trip_database.Table.SiteInformation;
import com.example.git_set_code.trip_database.Table.SourceInformation;
import com.example.git_set_code.trip_database.Table.Trailer;
import com.example.git_set_code.trip_database.Table.Trip;
import com.example.git_set_code.trip_database.Table.Truck;

import java.util.List;

public class TripViewModel extends AndroidViewModel {
    private TripRepository tripRepository;
    private LiveData<List<Driver>> allDrivers;
    private LiveData<List<SiteInformation>> allSite;
    private LiveData<List<SourceInformation>> allSource;
    private LiveData<List<Trailer>> allTrailer;
    private LiveData<List<Truck>> allTruck;
    private LiveData<List<Trip>> allTrip;

    public TripViewModel(@NonNull Application application) {
        super(application);
        tripRepository = new TripRepository(application);
        allDrivers = tripRepository.getGetAllDrivers();
        allSite = tripRepository.getGetAllSite();
        allSource = tripRepository.getGetAllSource();
        allTrailer = tripRepository.getGetAllTrailer();
        allTruck = tripRepository.getGetAllTruck();
        allTrip = tripRepository.getGetAllTrip();
    }

    public LiveData<List<Driver>> getAllDrivers() {
        return allDrivers;
    }

    public LiveData<List<SiteInformation>> getAllSite() {
        return allSite;
    }

    public LiveData<List<SourceInformation>> getAllSource() {
        return allSource;
    }

    public LiveData<List<Trailer>> getAllTrailer() {
        return allTrailer;
    }

    public LiveData<List<Truck>> getAllTruck() {
        return allTruck;
    }

    public LiveData<List<Trip>> getAllTrip() {
        return allTrip;
    }
    public void extractData(){
        tripRepository.extractData();
    }
    public void insertDrivers(Driver allDrivers) {
        tripRepository.insertDriver(allDrivers);
    }

    public void insertSite(SiteInformation siteInformation) {
        tripRepository.insertSiteInformation(siteInformation);
    }

    public void insertSource(SourceInformation sourceInformation) {
        tripRepository.insertSourceinformation(sourceInformation);
    }

    public void insertTrailer(Trailer trailer) {
        tripRepository.insertTrailer(trailer);
    }

    public void insertTruck(Truck truck) {
        tripRepository.insertTruck(truck);
    }

    public void insertTrip(Trip trip) {
        tripRepository.insertTrip(trip);
    }
}
