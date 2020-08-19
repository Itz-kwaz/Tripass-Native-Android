package dev.nkwachi.tripass_native;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements iMainActivity {

    private static final String TAG = "MainActivity";

    private TripViewModel mViewModel;
    private NavController navController;
    private SharedViewModel sharedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(MainActivity.this,R.id.nav_host_fragment);

        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        mViewModel = new ViewModelProvider(this).get(TripViewModel.class);
        mViewModel.getAllTrips().observe(this, trips -> sharedViewModel.setTripsList(trips));
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    public void insertTrip(Trip trip) {
        mViewModel.insert(trip);
        navController.navigate(R.id.homeFragment);
    }

    @Override
    public void deleteTrip(Trip trip) {
        mViewModel.delete(trip);

    }

    @Override
    public void updateTrip(Trip trip) {
        mViewModel.update(trip);
    }

    @Override
    public void sendTrip(Trip trip) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("Trip",trip);
        navController.navigate(R.id.addTripFragment,bundle);
    }
}