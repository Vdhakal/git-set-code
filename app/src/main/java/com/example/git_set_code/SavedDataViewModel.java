package com.example.git_set_code;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SavedDataViewModel extends AndroidViewModel {
    private SavedDataRepository repository;
    private LiveData<List<SavedData>> allData;

    public SavedDataViewModel(@NonNull Application application) {
        super(application);
        repository = new SavedDataRepository(application);
        allData = repository.getAllData();
    }
    public void insert(SavedData savedData) {
        repository.insert(savedData);
    }
    public void delete(SavedData savedData) {
        repository.delete(savedData);
    }
    public LiveData<List<SavedData>> getAllData() {
        return allData;
    }
}
