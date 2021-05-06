package com.example.git_set_code.viewmodels;


import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.git_set_code.cache.TripsObject;
import com.example.git_set_code.cache.TripsObjectRepository;
import com.example.git_set_code.fragments.HomeFragment;

import java.util.List;

/**
 * ViewModel for the TripsObject
 */
public class TripsObjectViewModel extends AndroidViewModel {
    private TripsObjectRepository tripsObjectRepository;
    private LiveData<List<TripsObject>> getAlltripObjects;
    private List<TripsObject> getAllTrips;

    /**
     * Constructor for TripsObjectViewModel
     * @param application, an Application
     */
    public TripsObjectViewModel(@NonNull Application application) {
        super(application);
        tripsObjectRepository = new TripsObjectRepository(application);
        getAlltripObjects = tripsObjectRepository.getAllTrips();
        getAllTrips = tripsObjectRepository.getTrips();
    }

    /**
     * This interface gives other classes to implement this blue print to modify according to their requirements
     */
    public interface ViewModelInsertListener {
        void onError(String message);

        void onResponse();
    }

    /**
     * Getter for getAlltripObjects
     * @return getAlltripObjects, the list LiveData<List<TripsObject>>
     */
    public LiveData<List<TripsObject>> getGetAlltripObjects() {
        return getAlltripObjects;
    }

    /**
     * Getter for getAllTrips
     * @return getAllTrips, the list List<TripsObject>
     */
    public List<TripsObject> getAllTrips() {
        return getAllTrips;
    }

    /**
     * For insertion
     * @param tripsObject, a TripsObject object
     * @param context, a Context object
     */
    public void insert(TripsObject tripsObject, Context context) {
        tripsObjectRepository.insert(tripsObject, new TripsObjectRepository.InsertResponseListener() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse() {
            }
        }, context);
    }

    /**
     * This method deletes the tripsObjectRepository
     */
    public void delete() {
        tripsObjectRepository.delete();
    }

}
