package dev.nkwachi.tripass_native;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TripViewModel extends AndroidViewModel {

    private TripRepository tripRepository;
    private LiveData<List<Trip>> allTrips;

    public TripViewModel(@NonNull Application application) {
        super(application);
        tripRepository = new TripRepository(application);
        allTrips = tripRepository.getAllTrips();
    }

    public LiveData<List<Trip>> getAllTrips(){
        return allTrips;
    }

    public void insert(Trip trip){
        tripRepository.insertTrip(trip);
    }

    public void update(Trip trip){
        tripRepository.updateTrip(trip);
    }

    public void delete(Trip trip){
        tripRepository.deleteTrip(trip);
    }
}
