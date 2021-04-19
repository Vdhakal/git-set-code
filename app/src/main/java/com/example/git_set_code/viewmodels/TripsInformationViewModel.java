package com.example.git_set_code.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.git_set_code.cache.TripsDataRoom;
import com.example.git_set_code.database.SavedData;
import com.example.git_set_code.repository.TripsDataRepository;

import java.util.List;

public class TripsInformationViewModel extends AndroidViewModel {
    private TripsDataRepository tripsDataRepository;
    private LiveData<List<TripsDataRoom>> allTrips;
    public TripsInformationViewModel(@NonNull Application application) {
        super(application);
        tripsDataRepository = new TripsDataRepository(application);
        allTrips = tripsDataRepository.getAllData();
    }

    public void insert(TripsDataRoom tripsDataRoom) {
        tripsDataRepository.insert(tripsDataRoom);
    }
    public void update(TripsDataRoom tripsDataRoom) {
        tripsDataRepository.update(tripsDataRoom);
    }
    public void delete(TripsDataRoom tripsDataRoom) {
        tripsDataRepository.delete(tripsDataRoom);
    }
    public LiveData<List<TripsDataRoom>> getAllData() {
        return allTrips;
    }
}
