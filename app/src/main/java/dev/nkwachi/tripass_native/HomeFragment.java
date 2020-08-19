package dev.nkwachi.tripass_native;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeFragment extends Fragment {

    private TripAdapter tripAdapter;
    private static final String TAG = "HomeFragment";
    private TextView tripCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Trip>>() {
            @Override
            public void onChanged(List<Trip> trips) {
                tripAdapter.setAllTrips(trips);
                tripCount.setText(trips.size() +" " +  getString(R.string.trips));

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");

        View view = inflater.inflate(R.layout.fragment_home, container, false);


        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tripCount = view.findViewById(R.id.trip_count);
        tripAdapter = new TripAdapter(getActivity());
        recyclerView.setAdapter(tripAdapter);
        FloatingActionButton floatingActionButton =view.findViewById(R.id.floatingActionButton2);

        floatingActionButton.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.addTripFragment));


        return view;
    }

    public void  setTrips(List<Trip> tripList)
    {
        tripAdapter.setAllTrips(tripList);
    }
}