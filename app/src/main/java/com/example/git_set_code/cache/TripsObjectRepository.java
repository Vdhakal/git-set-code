package com.example.git_set_code.cache;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

/**
 *
 */
public class TripsObjectRepository {
    private TripsObjectDao tripsObjectDao;
    private LiveData<List<TripsObject>> allTripObjects;
    private List<TripsObject> allTrips;

    /**
     * @param application
     */
    public TripsObjectRepository(Application application) {
        TripsObjectDatabase db = TripsObjectDatabase.getInstance(application);
        tripsObjectDao = db.tripsObjectDao();
        allTripObjects = tripsObjectDao.getAllTripInformation();
    }

    /**
     * @param tripsObject
     * @param insertResponseListener
     * @param context
     */
    public void insert(TripsObject tripsObject, InsertResponseListener insertResponseListener, Context context) {
        new insertAsyncTask(tripsObjectDao).execute(tripsObject);
        insertResponseListener.onResponse();
    }

    /**
     *
     */
    public void delete() {
        new deleteAsyncTask(tripsObjectDao).execute();
    }

    /**
     * @return
     */
    public LiveData<List<TripsObject>> getAllTrips() {
        return allTripObjects;
    }

    /**
     * @return
     */
    public List<TripsObject> getTrips() {
        return allTrips;
    }

    /**
     *
     */
    public interface InsertResponseListener {
        void onError(String message);

        void onResponse();
    }

    /**
     *
     */
    private static class insertAsyncTask extends AsyncTask<TripsObject, Void, Void> {

        private TripsObjectDao mAsyncTaskDao;

        insertAsyncTask(TripsObjectDao dao) {
            mAsyncTaskDao = dao;
        }

        /**
         * @param params
         * @return
         */
        @Override
        protected Void doInBackground(final TripsObject... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    /**
     *
     */
    private static class deleteAsyncTask extends AsyncTask<TripsObject, Void, Void> {

        /**
         *
         */
        private TripsObjectDao mAsyncTaskDao;

        deleteAsyncTask(TripsObjectDao dao) {
            mAsyncTaskDao = dao;
        }

        /**
         * @param tripsObjects
         * @return
         */
        @Override
        protected Void doInBackground(TripsObject... tripsObjects) {
            mAsyncTaskDao.delete();
            return null;
        }
    }

}
