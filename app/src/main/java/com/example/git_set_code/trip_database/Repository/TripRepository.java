package com.example.git_set_code.trip_database.Repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.git_set_code.apiHelpers.TripsAPIService;
import com.example.git_set_code.trip_database.Dao.TripDao;
import com.example.git_set_code.trip_database.Database.TripDatabase;
import com.example.git_set_code.trip_database.Table.Driver;
import com.example.git_set_code.trip_database.Table.SiteForm;
import com.example.git_set_code.trip_database.Table.SiteInformation;
import com.example.git_set_code.trip_database.Table.SourceForm;
import com.example.git_set_code.trip_database.Table.SourceInformation;
import com.example.git_set_code.trip_database.Table.Trailer;
import com.example.git_set_code.trip_database.Table.Trip;
import com.example.git_set_code.trip_database.Table.TripClientData;
import com.example.git_set_code.trip_database.Table.Truck;
import com.example.git_set_code.utils.CustomToast;
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
    private  LiveData<List<TripClientData>> getAllTripClient;
    private LiveData<List<Double>> sourceLatitudes;
    private LiveData<List<Double>> sourceLongitudes;
    private LiveData<List<Double>> siteLatitudes;
    private LiveData<List<Double>> siteLongitudes;
    private LiveData<List<SourceForm>> sourceForms;
    private LiveData<List<SiteForm>> siteForms;
    private int getSelected = 0;
    List<Driver> driverObjectList;
    List<SiteInformation> siteInformationObjectList;
    List<SourceInformation> sourceInformationObjectList;
    List<Truck> truckObjectList;
    List<Trailer> trailerObjectList;
    List<Trip> tripObjectList;
    List<TripClientData> tripClientData;
    LiveData<List<Integer>> selections;

    public TripRepository(Application application){
        TripDatabase tripDatabase = TripDatabase.getInstance(application);
        tripDao = tripDatabase.tripDao();
        getAllDrivers = tripDao.getAllDrivers();
        getAllSite = tripDao.getAllSite();
        getAllSource = tripDao.getAllSource();
        getAllTrailer = tripDao.getAllTrailer();
        getAllTruck = tripDao.getAllTruck();
        getAllTrip = tripDao.getAllTrip();
        selections = tripDao.getSelected();
        sourceLatitudes = tripDao.getSourceLatitude();
        sourceLongitudes= tripDao.getSourceLongitude();
        siteLatitudes= tripDao.getSiteLatitude();
        siteLongitudes= tripDao.getSiteLongitude();
        getAllTripClient = tripDao.getAllTripClientData();
        thisContext = application.getApplicationContext();
        driverObjectList= new ArrayList<>();
        siteInformationObjectList= new ArrayList<>();
        sourceInformationObjectList= new ArrayList<>();
        truckObjectList= new ArrayList<>();
        trailerObjectList= new ArrayList<>();
        tripObjectList= new ArrayList<>();
        tripClientData= new ArrayList<>();
    }

    public LiveData<List<SourceForm>> getSourceForms() {
        return sourceForms;
    }

    public LiveData<List<SiteForm>> getSiteForms() {
        return siteForms;
    }

    public LiveData<List<Integer>> getGetSelected() {
        return selections;
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
    public LiveData<List<TripClientData>> getGetAllTripClient() {
        return getAllTripClient;
    }

    public LiveData<List<Double>> getSourceLatitudes() {
        return sourceLatitudes;
    }

    public LiveData<List<Double>> getSourceLongitudes() {
        return sourceLongitudes;
    }

    public LiveData<List<Double>> getSiteLatitudes() {
        return siteLatitudes;
    }

    public LiveData<List<Double>> getSiteLongitudes() {
        return siteLongitudes;
    }

    public void extractData(){
        TripsAPIService tripsAPIService = new TripsAPIService();
        tripsAPIService.getRequestedJsonForRepo(thisContext, driverObjectList, siteInformationObjectList,  sourceInformationObjectList,truckObjectList, trailerObjectList, tripObjectList, tripClientData, new TripsAPIService.VolleyResponseListener() {
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
                for(int i =0; i<tripClientData.size(); i++){
                    insertTripClientData(tripClientData.get(i));
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
    public void setSelection(){
        TripDatabase.databaseWriteExecutor.execute(() -> {
            tripDao.setSelection();
        });
    }
    public void insertTripClientData(TripClientData tripClientData){
        TripDatabase.databaseWriteExecutor.execute(() -> {
            tripDao.insertTripClientData(tripClientData);
        });
    }

    public void insetSourceForms(SourceForm sourceForms) {
        Log.i("Reposit", "reached repo");
        TripDatabase.databaseWriteExecutor.execute(() -> {
            tripDao.insertSourceForm(sourceForms);
        });
    }

    public void insertSiteForms(SiteForm siteForms) {
        TripDatabase.databaseWriteExecutor.execute(() -> {
            tripDao.insertSiteForm(siteForms);
        });
    }
}
