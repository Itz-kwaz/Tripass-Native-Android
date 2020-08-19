package dev.nkwachi.tripass_native;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<List<Trip>> mutableLiveData = new MutableLiveData<List<Trip>>();

    public void setTripsList(List<Trip> tripsList){
        mutableLiveData.setValue(tripsList);
    }

    public MutableLiveData<List<Trip>> getMutableLiveData()
    {
        return mutableLiveData;
    }
}
