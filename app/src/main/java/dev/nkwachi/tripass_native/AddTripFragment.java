package dev.nkwachi.tripass_native;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class AddTripFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "AddTripFragment";

    private Trip trip;
    private EditText departureCity;
    private EditText departureDate;
    private EditText departureTime;
    private EditText destinationCity;
    private EditText arrivalDate;
    private EditText arrivalTime;

    private iMainActivity iMainActivity;
    private int tripType = 1;
    private ArrayAdapter<CharSequence> adapter;
    private Spinner spinner;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        iMainActivity = (iMainActivity) context;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
        trip = getArguments().getParcelable("Trip");

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_trip, container, false);
        departureCity = view.findViewById(R.id.departure);
        departureDate = view.findViewById(R.id.departure_date);
        departureTime = view.findViewById(R.id.arrival_time);

        destinationCity = view.findViewById(R.id.Destination);
        arrivalDate = view.findViewById(R.id.arrival_date);
        arrivalTime = view.findViewById(R.id.departure_time);

        spinner = view.findViewById(R.id.spinner);

        TextView textView = view.findViewById(R.id.textView4);

        adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.trip_type_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Button button = view.findViewById(R.id.button);
        if(trip != null)
        {
            button.setText(R.string.updateTrip);
            textView.setText(R.string.updateATrip);
        }
        button.setOnClickListener(view1 -> {

            if(validateFields()){
                if(trip == null){
                    trip = new Trip();
                    updateValues();
                    iMainActivity.insertTrip(trip);
                }else {
                    updateValues();
                    iMainActivity.updateTrip(trip);
                }
            }else{
                Toast.makeText(getActivity(),"Fill Empty fields",Toast.LENGTH_SHORT).show();
            }



        });
        showDatePicker(departureDate);
        showTimePicker(departureTime);
       showTimePicker(arrivalTime);
       showDatePicker(arrivalDate);

        setValues();
        return view;
    }

    private void setValues(){
        if(trip != null){
            departureCity.setText(trip.getDepartureCity());
            departureDate.setText(trip.getDepartureDate());
            departureTime.setText(trip.getDepartureTime());

            destinationCity.setText(trip.getDestinationCity());
            arrivalDate.setText(trip.getArrivalDate());
            arrivalTime.setText(trip.getArrivalTime());

            spinner.setSelection(trip.getTripType());


        }

    }

    private void updateValues()
    {
        trip.setArrivalDate(arrivalDate.getText().toString());
        trip.setDepartureCity(departureCity.getText().toString());
        trip.setArrivalTime(arrivalTime.getText().toString());

        trip.setTripType(tripType);

        trip.setDestinationCity(destinationCity.getText().toString());
        trip.setDepartureDate(departureDate.getText().toString());
        trip.setDepartureTime(departureTime.getText().toString());
    }

    private boolean validateFields()
    {
        return !destinationCity.getText().toString().isEmpty() && !departureCity.getText().toString().isEmpty() &&
                !departureDate.getText().toString().isEmpty() && !departureTime.getText().toString().isEmpty()
                && !arrivalDate.getText().toString().isEmpty() && !arrivalTime.getText().toString().isEmpty();

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
       tripType = position;
        Log.d(TAG, "onItemSelected: "+ tripType);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showDatePicker(EditText editText)
    {
       editText.setOnClickListener(view -> {
                   final Calendar cldr = Calendar.getInstance();
                   int day = cldr.get(Calendar.DAY_OF_MONTH);
                   int month = cldr.get(Calendar.MONTH);
                   int year = cldr.get(Calendar.YEAR);
                   DatePickerDialog datePickerDialog = new DatePickerDialog(requireActivity(), (datePicker, Year, monthOfYear, dayOfMonth) -> {
                       editText.setText(dayOfMonth + "/" + (monthOfYear + 1) +"/" +Year);

                   },year,month,day);
                   datePickerDialog.show();
               }
       );

    }

    private void showTimePicker(EditText editText){
        editText.setOnClickListener(view -> {
            final Calendar cldr = Calendar.getInstance();
            int hour = cldr.get(Calendar.HOUR_OF_DAY);
            int minutes = cldr.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(requireActivity(),
                    (timePicker, hourOfDay, minuteOfDay) -> editText.setText(hourOfDay + ":" +minuteOfDay),hour,minutes,
                    false);
            timePickerDialog.show();
        });


    }
}