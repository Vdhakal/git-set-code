package com.example.git_set_code.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.git_set_code.database.SavedData;
import com.example.git_set_code.repository.SavedDataRepository;

import java.util.List;

/**
 *
 */
public class SavedDataViewModel extends AndroidViewModel {
    private SavedDataRepository repository;
    private LiveData<List<SavedData>> allData;

    /**
     * @param application
     */
    public SavedDataViewModel(@NonNull Application application) {
        super(application);
        repository = new SavedDataRepository(application);
        allData = repository.getAllData();
    }

    /**
     * @param savedData
     */
    public void insert(SavedData savedData) {
        repository.insert(savedData);
    }

    /**
     * @param savedData
     */
    public void delete(SavedData savedData) {
        repository.delete(savedData);
    }

    /**
     * @return
     */
    public LiveData<List<SavedData>> getAllData() {
        return allData;
    }
}
