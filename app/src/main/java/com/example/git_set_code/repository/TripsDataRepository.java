//package com.example.git_set_code.repository;
//
//import android.app.Application;
//import android.os.AsyncTask;
//
//import androidx.lifecycle.LiveData;
//import com.example.git_set_code.cache.TripsDataDao;
//import com.example.git_set_code.cache.TripsDataRoom;
//import com.example.git_set_code.cache.TripsDatabase;
//
//import java.util.List;
//
//public class TripsDataRepository {
//    private TripsDataDao tripsDataDao;
//    private LiveData<List<TripsDataRoom>> allTripData;
//
//    public TripsDataRepository(Application application){
//        TripsDatabase tripsDatabase = TripsDatabase.getInstance(application);
//        tripsDataDao = tripsDatabase.tripsDataDao();
//        allTripData = tripsDataDao.getAllTripInformation();
//    }
//    public void insert(TripsDataRoom tripsDataRoom){
//        new TripsDataRepository.InsertTripsDataAsyncTask(tripsDataDao).execute(tripsDataRoom);
//    }
//
//    public void update(TripsDataRoom tripsDataRoom){
//        new TripsDataRepository.DeleteTripsDataAsyncTask(tripsDataDao).execute(tripsDataRoom);
//    }
//    public void delete(TripsDataRoom tripsDataRoom){
//        new TripsDataRepository.UpdateTripsDataAsyncTask(tripsDataDao).execute(tripsDataRoom);
//    }
//
//    public LiveData<List<TripsDataRoom>> getAllData() {
//        return allTripData;
//    }
//
//    private static class InsertTripsDataAsyncTask extends AsyncTask<TripsDataRoom, Void, Void> {
//        private TripsDataDao tripsDataDao;
//
//        private InsertTripsDataAsyncTask(TripsDataDao tripsDataDao){
//            this.tripsDataDao = tripsDataDao;
//        }
//
//        @Override
//        protected Void doInBackground(TripsDataRoom... tripsDataRooms) {
//            tripsDataDao.insert(tripsDataRooms[0]);
//            return null;
//        }
//    }
//    private static class DeleteTripsDataAsyncTask extends AsyncTask<TripsDataRoom, Void, Void> {
//        private TripsDataDao tripsDataDao;
//
//        private DeleteTripsDataAsyncTask(TripsDataDao tripsDataDao){
//            this.tripsDataDao = tripsDataDao;
//        }
//
//        @Override
//        protected Void doInBackground(TripsDataRoom... tripsDataRooms) {
//            tripsDataDao.insert(tripsDataRooms[0]);
//            return null;
//        }
//    }
//
//    private static class UpdateTripsDataAsyncTask extends AsyncTask<TripsDataRoom, Void, Void> {
//        private TripsDataDao tripsDataDao;
//
//        private UpdateTripsDataAsyncTask(TripsDataDao tripsDataDao){
//            this.tripsDataDao = tripsDataDao;
//        }
//
//        @Override
//        protected Void doInBackground(TripsDataRoom... tripsDataRooms) {
//            tripsDataDao.insert(tripsDataRooms[0]);
//            return null;
//        }
//    }
//}
