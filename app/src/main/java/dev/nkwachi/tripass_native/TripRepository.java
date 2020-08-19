package dev.nkwachi.tripass_native;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TripRepository {

    private TripDao tripDao;
    private static final String TAG = "TripRepository";

    private LiveData<List<Trip>> mAllTrips;

    TripRepository(Context context){
        Log.d(TAG, "TripRepository: Initializing ");
        TripDatabase db = TripDatabase.getDatabase(context);
        tripDao = db.tripDao();
        mAllTrips = tripDao.getAllTrips();
        if(mAllTrips != null)
        Log.d(TAG, "TripRepository: Initializing ");
    }

    LiveData<List<Trip>> getAllTrips(){
        return mAllTrips;
    }

    void insertTrip(Trip trip){
        Log.d(TAG, "insertTrip: ");
        TripDatabase.databaseWriteExecutor.execute(()-> tripDao.insertTrip(trip));
    }

    void deleteTrip(Trip trip){
        TripDatabase.databaseWriteExecutor.execute(()-> tripDao.deleteTrip(trip));
    }

    void updateTrip(Trip trip){
        TripDatabase.databaseWriteExecutor.execute(()-> tripDao.updateTrip(trip));
    }
}
