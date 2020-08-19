package dev.nkwachi.tripass_native;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.MyViewHolder> {
    private static final String TAG = "TripAdapter";

    private List<Trip> allTrips;
    private Context mContext;
    private iMainActivity iMainActivity;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        iMainActivity = (iMainActivity) mContext;
    }

    public TripAdapter(Context context) {
        mContext = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        if(allTrips != null)
        {
            Log.d(TAG, "onBindViewHolder: ");
            Trip trip = allTrips.get(position);

            holder.destinationCity.setText(trip.getDestinationCity());
            holder.arrivalTime.setText(trip.getArrivalTime());
            holder.arrivalDate.setText(trip.getArrivalDate());

            holder.departureDate.setText(trip.getDepartureDate());
            holder.departureTime.setText(trip.getDepartureTime());
            holder.departureCity.setText(trip.getDepartureCity());
            GradientDrawable gradientDrawable = (GradientDrawable) holder.tripType.getBackground().mutate();

            switch (trip.getTripType()){
                case 0:
                    holder.tripType.setText(R.string.business);
                    gradientDrawable.setColor(Color.parseColor("#2962FF"));
                    break;
                case 1:
                    holder.tripType.setText(R.string.vacation);
                    gradientDrawable.setColor(Color.parseColor("#FF6D00"));
                    break;
                case 2:
                    holder.tripType.setText(R.string.education);
                    gradientDrawable.setColor(Color.parseColor("#00C853"));
                default:
                    holder.tripType.setText(R.string.health);
                    gradientDrawable.setColor(Color.parseColor("#D129ED"));
                    break;

            }

            holder.popUpMenu.setOnClickListener(view -> {

                PopupMenu popupMenu = new PopupMenu(mContext,view);
                popupMenu.setOnMenuItemClickListener(menuItem -> {
                    switch (menuItem.getItemId()){
                        case R.id.update:
                            iMainActivity.sendTrip(trip);
                            return true;
                        case R.id.delete:
                            iMainActivity.deleteTrip(trip);
                            notifyDataSetChanged();
                            return true;
                        default:
                            return false;
                    }
                });
                popupMenu.inflate(R.menu.menu);
                popupMenu.show();
            });
        }
    }

    void setAllTrips(List<Trip> trips){

        allTrips = trips;
        notifyDataSetChanged();
        Log.d(TAG, "setAllTrips: ");
    }

    @Override
    public int getItemCount() {
        if(allTrips != null)
        return allTrips.size();

        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView departureCity;
        private TextView departureTime;
        private TextView departureDate;
        private TextView arrivalDate;
        private TextView arrivalTime;
        private TextView destinationCity;
        private TextView tripType;
        private TextView popUpMenu;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            departureCity = itemView.findViewById(R.id.departure_city);
            departureTime = itemView.findViewById(R.id.arrival_time);
            departureDate = itemView.findViewById(R.id.departure_date);

            arrivalDate = itemView.findViewById(R.id.arrival_date);
            arrivalTime = itemView.findViewById(R.id.departure_time);
            destinationCity = itemView.findViewById(R.id.destination_city);

            popUpMenu = itemView.findViewById(R.id.popUpMenu);

            tripType = itemView.findViewById(R.id.trip_type);

        }
    }
}
