package dev.nkwachi.tripass_native;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Trip_table")
public class Trip implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int Id;

    protected Trip(Parcel in) {
        Id = in.readInt();
        departureCity = in.readString();
        departureTime = in.readString();
        tripType = in.readInt();
        departureDate = in.readString();
        destinationCity = in.readString();
        arrivalDate = in.readString();
        arrivalTime = in.readString();
    }

    public static final Creator<Trip> CREATOR = new Creator<Trip>() {
        @Override
        public Trip createFromParcel(Parcel in) {
            return new Trip(in);
        }

        @Override
        public Trip[] newArray(int size) {
            return new Trip[size];
        }
    };

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @NonNull
    private String departureCity;

    @NonNull
    private String departureTime;

    @NonNull
    private int tripType;

    @NonNull
    private String departureDate;

    @NonNull
    private String destinationCity;

    @NonNull
    private String arrivalDate;


    public int getTripType() {
        return tripType;
    }

    public void setTripType(int tripType) {
        this.tripType = tripType;
    }

    public Trip() {
    }

    @NonNull
    private String arrivalTime;

    public Trip(@NonNull String departureCity, @NonNull String departureTime, @NonNull String departureDate,
                @NonNull int tripType, @NonNull String destinationCity, @NonNull String arrivalDate,
                @NonNull String arrivalTime)
    {
        this.departureCity = departureCity;
        this.departureTime = departureTime;
        this.departureDate = departureDate;
        this.destinationCity = destinationCity;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.tripType = tripType;
    }

    @NonNull
    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(@NonNull String departureCity) {
        this.departureCity = departureCity;
    }

    @NonNull
    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(@NonNull String departureTime) {
        this.departureTime = departureTime;
    }

    @NonNull
    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(@NonNull String departureDate) {
        this.departureDate = departureDate;
    }

    @NonNull
    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(@NonNull String destinationCity) {
        this.destinationCity = destinationCity;
    }

    @NonNull
    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(@NonNull String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @NonNull
    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(@NonNull String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Id);
        parcel.writeString(departureCity);
        parcel.writeString(departureTime);
        parcel.writeInt(tripType);
        parcel.writeString(departureDate);
        parcel.writeString(destinationCity);
        parcel.writeString(arrivalDate);
        parcel.writeString(arrivalTime);
    }
}
