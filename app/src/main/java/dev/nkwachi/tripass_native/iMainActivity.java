package dev.nkwachi.tripass_native;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface iMainActivity {

    void insertTrip(Trip trip);

    void deleteTrip(Trip trip);

    void updateTrip(Trip trip);

    void sendTrip(Trip trip);
}
