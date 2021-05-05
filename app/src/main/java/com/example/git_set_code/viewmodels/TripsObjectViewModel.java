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
 *
 */
public class TripsObjectViewModel extends AndroidViewModel {
    private TripsObjectRepository tripsObjectRepository;
    private LiveData<List<TripsObject>> getAlltripObjects;
    private List<TripsObject> getAllTrips;

    /**
     * @param application
     */
    public TripsObjectViewModel(@NonNull Application application) {
        super(application);
        tripsObjectRepository = new TripsObjectRepository(application);
        getAlltripObjects = tripsObjectRepository.getAllTrips();
        getAllTrips = tripsObjectRepository.getTrips();
    }

    /**
     *
     */
    public interface ViewModelInsertListener {
        void onError(String message);

        void onResponse();
    }

    /**
     * @return
     */
    public LiveData<List<TripsObject>> getGetAlltripObjects() {
        return getAlltripObjects;
    }

    /**
     * @return
     */
    public List<TripsObject> getAllTrips() {
        return getAllTrips;
    }

    /**
     * @param tripsObject
     * @param context
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
     *
     */
    public void delete() {
        tripsObjectRepository.delete();
    }

}
