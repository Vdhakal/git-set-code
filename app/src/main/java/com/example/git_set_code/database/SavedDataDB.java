package com.example.git_set_code.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 *
 */
@Database(entities = {SavedData.class}, version = 1)
public abstract class SavedDataDB extends RoomDatabase {
    private static SavedDataDB instance;

    public abstract SavedDataDao savedDataDao();

    /**
     * @param context
     * @return
     */
    public static synchronized SavedDataDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SavedDataDB.class, "saved_data").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return instance;

    }

    /**
     *
     */
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
//            new PopulateSavedDataAsyncTask(instance).execute();
        }
    };
//    private static class PopulateSavedDataAsyncTask extends AsyncTask<Void, Void, Void> {
//        private SavedDataDao savedDataDao;
//
//        private PopulateSavedDataAsyncTask(SavedDataDB savedDataDB){
//            savedDataDao = savedDataDB.savedDataDao();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            savedDataDao.insert(new SavedData(0.00,0.00,"text"));
//            return null;
//        }
//    }
}
