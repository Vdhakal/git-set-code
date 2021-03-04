package com.example.git_set_code.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.git_set_code.database.SavedData;
import com.example.git_set_code.database.SavedDataDB;
import com.example.git_set_code.database.SavedDataDao;

import java.util.List;

public class SavedDataRepository {
    private SavedDataDao savedDataDao;
    private LiveData<List<SavedData>> allData;

    public SavedDataRepository(Application application){
        SavedDataDB database = SavedDataDB.getInstance(application);
        savedDataDao = database.savedDataDao();
        allData = savedDataDao.getAllData();
    }

    public void insert(SavedData savedData){
        new InsertSavedDataAsyncTask(savedDataDao).execute(savedData);
    }

    public void delete(SavedData savedData){
        new DeleteSavedDataAsyncTask(savedDataDao).execute(savedData);
    }

    public LiveData<List<SavedData>> getAllData() {
        return allData;
    }

    private static class InsertSavedDataAsyncTask extends AsyncTask<SavedData, Void, Void> {
        private SavedDataDao savedDataDao;

        private InsertSavedDataAsyncTask(SavedDataDao savedDataDao){
            this.savedDataDao = savedDataDao;
        }

        @Override
        protected Void doInBackground(SavedData... savedData) {
            savedDataDao.insert(savedData[0]);
            return null;
        }
    }
    private static class DeleteSavedDataAsyncTask extends AsyncTask<SavedData, Void, Void> {
        private SavedDataDao savedDataDao;

        private DeleteSavedDataAsyncTask(SavedDataDao savedDataDao){
            this.savedDataDao = savedDataDao;
        }

        @Override
        protected Void doInBackground(SavedData... savedData) {
            savedDataDao.delete(savedData[0]);
            return null;
        }
    }
}
