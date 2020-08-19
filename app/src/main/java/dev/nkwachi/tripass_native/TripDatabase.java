package dev.nkwachi.tripass_native;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Trip.class},version = 1,exportSchema = false)
public abstract class TripDatabase extends RoomDatabase {

    public abstract TripDao tripDao();

    private static volatile TripDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 5;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static TripDatabase getDatabase(Context context)
    {
        if(INSTANCE == null)
        {
            synchronized (TripDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TripDatabase.class, "trip_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
