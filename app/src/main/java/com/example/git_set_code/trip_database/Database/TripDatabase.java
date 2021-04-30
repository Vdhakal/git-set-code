package com.example.git_set_code.trip_database.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.git_set_code.cache.TripsObjectDatabase;
import com.example.git_set_code.trip_database.Dao.TripDao;
import com.example.git_set_code.trip_database.Table.Driver;
import com.example.git_set_code.trip_database.Table.SiteInformation;
import com.example.git_set_code.trip_database.Table.SourceInformation;
import com.example.git_set_code.trip_database.Table.Trailer;
import com.example.git_set_code.trip_database.Table.Trip;
import com.example.git_set_code.trip_database.Table.TripClientData;
import com.example.git_set_code.trip_database.Table.Truck;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Driver.class, Trip.class, Truck.class, Trailer.class, SourceInformation.class, SiteInformation.class, TripClientData.class},
        version = 2)
public abstract class TripDatabase extends RoomDatabase {
    public abstract TripDao tripDao();
    private static TripDatabase tripDatabase;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static TripDatabase getInstance(final Context context) {
        if (tripDatabase == null) {
            synchronized (TripDatabase.class) {
                tripDatabase = Room.databaseBuilder(context.getApplicationContext(),
                        TripDatabase.class, "saved_data").fallbackToDestructiveMigration().addCallback(sRoomDatabaseCallback).build();
            }
        }
        return tripDatabase;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                TripDao dao = tripDatabase.tripDao();

//                Driver driver = new Driver("N/A","N/A");
//                dao.insertDrivers(driver);
//                Trip trip = new Trip(0,"N/A","N/A","N/A");
//                dao.insertTrip(trip);
//                SiteInformation siteInformation = new SiteInformation("N/A","N/A",0, 0,0,"N/A", "N/A",0, "N/A", "N/A", 0,0,"N/A","N/A","N/A", "N/A", "N/A", "N/A",0,0,"N/A",0);
//                dao.insertSite(siteInformation);
//                SourceInformation sourceInformation = new SourceInformation(0,"N/A",0, 0,"N/A","N/A", "N/A","N/A", "N/A", "N/A", 0,0);
//                dao.insertSource(sourceInformation);
//                Truck truck = new Truck(0,"N/A","N/A", "N/A");
//                dao.insertTruck(truck);
//                Trailer trailer = new Trailer(0,"N/A","N/A",0);
//                dao.insertTrailer(trailer);
            });
        }
    };
    public void cleanUp(){
        tripDatabase = null;
    }
}
