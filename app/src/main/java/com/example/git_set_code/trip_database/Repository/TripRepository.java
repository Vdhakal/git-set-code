package com.example.git_set_code.trip_database.Repository;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.git_set_code.apiHelpers.TripsAPIService;
import com.example.git_set_code.trip_database.Dao.TripDao;
import com.example.git_set_code.trip_database.Database.TripDatabase;
import com.example.git_set_code.trip_database.Table.Driver;
import com.example.git_set_code.trip_database.Table.SiteInformation;
import com.example.git_set_code.trip_database.Table.SourceInformation;
import com.example.git_set_code.trip_database.Table.Trailer;
import com.example.git_set_code.trip_database.Table.Trip;
import com.example.git_set_code.trip_database.Table.Truck;
import com.example.git_set_code.viewmodels.TripsData;


import java.util.ArrayList;
import java.util.List;

public class TripRepository {
    private TripDao tripDao;
    private Context thisContext;
    private TripsAPIService tripsAPIService;
    private List<TripsData> tripsDataList;
    private LiveData<List<Driver>> getAllDrivers;
    private  LiveData<List<SiteInformation>> getAllSite;
    private  LiveData<List<SourceInformation>> getAllSource;
    private  LiveData<List<Trailer>> getAllTrailer;
    private  LiveData<List<Truck>> getAllTruck;
    private  LiveData<List<Trip>> getAllTrip;
    List<Driver> driverObjectList;
    List<SiteInformation> siteInformationObjectList;
    List<SourceInformation> sourceInformationObjectList;
    List<Truck> truckObjectList;
    List<Trailer> trailerObjectList;
    List<Trip> tripObjectList;

    public TripRepository(Application application){
        TripDatabase tripDatabase = TripDatabase.getInstance(application);
        tripDao = tripDatabase.tripDao();
        getAllDrivers = tripDao.getAllDrivers();
        getAllSite = tripDao.getAllSite();
        getAllSource = tripDao.getAllSource();
        getAllTrailer = tripDao.getAllTrailer();
        getAllTruck = tripDao.getAllTruck();
        getAllTrip = tripDao.getAllTrip();
        thisContext = application.getApplicationContext();
        driverObjectList= new ArrayList<>();
        siteInformationObjectList= new ArrayList<>();
        sourceInformationObjectList= new ArrayList<>();
        truckObjectList= new ArrayList<>();
        trailerObjectList= new ArrayList<>();
        tripObjectList= new ArrayList<>();
    }

    public Context getThisContext() {
        return thisContext;
    }

    public LiveData<List<Driver>> getGetAllDrivers() {
        return getAllDrivers;
    }

    public LiveData<List<SiteInformation>> getGetAllSite() {
        return getAllSite;
    }

    public LiveData<List<SourceInformation>> getGetAllSource() {
        return getAllSource;
    }

    public LiveData<List<Trailer>> getGetAllTrailer() {
        return getAllTrailer;
    }

    public LiveData<List<Truck>> getGetAllTruck() {
        return getAllTruck;
    }

    public LiveData<List<Trip>> getGetAllTrip() {
        return getAllTrip;
    }

    public void extractData(){
        TripsAPIService tripsAPIService = new TripsAPIService();
        tripsAPIService.getRequestedJsonForRepo(thisContext, driverObjectList, siteInformationObjectList,  sourceInformationObjectList,truckObjectList, trailerObjectList, tripObjectList, new TripsAPIService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
            }

            @Override
            public void onResponse() {
                for(int i =0; i<driverObjectList.size(); i++){
                    insertDriver(driverObjectList.get(i));
                }
                for(int i =0; i<truckObjectList.size(); i++){
                    insertTruck(truckObjectList.get(i));
                }
                for(int i =0; i<trailerObjectList.size(); i++){
                    insertTrailer(trailerObjectList.get(i));
                }
                for(int i =0; i<tripObjectList.size(); i++){
                    insertTrip(tripObjectList.get(i));
                }
                for(int i =0; i<siteInformationObjectList.size(); i++){
                    insertSiteInformation(siteInformationObjectList.get(i));
                }
                for(int i =0; i<sourceInformationObjectList.size(); i++){
                    insertSourceinformation(sourceInformationObjectList.get(i));
                }
            }

        });
    }

    public void insertDriver(Driver driver){
        TripDatabase.databaseWriteExecutor.execute(() -> {
            tripDao.insertDrivers(driver);
        });
    }

    public void insertTrip(Trip trip){
        TripDatabase.databaseWriteExecutor.execute(() -> {
            tripDao.insertTrip(trip);
        });
    }

    public void insertTruck(Truck truck){
        TripDatabase.databaseWriteExecutor.execute(() -> {
            tripDao.insertTruck(truck);
        });
    }

    public void insertTrailer(Trailer trailer){
        TripDatabase.databaseWriteExecutor.execute(() -> {
            tripDao.insertTrailer(trailer);
        });
    }

    public void insertSiteInformation(SiteInformation siteInformation){
        TripDatabase.databaseWriteExecutor.execute(() -> {
            tripDao.insertSite(siteInformation);
        });
    }

    public void insertSourceinformation(SourceInformation sourceInformation){
        TripDatabase.databaseWriteExecutor.execute(() -> {
            tripDao.insertSource(sourceInformation);
        });
    }
}
