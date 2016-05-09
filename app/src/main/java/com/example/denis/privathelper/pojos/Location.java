package com.example.denis.privathelper.pojos;


import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {

    public final String  lat;
    public final String  lng;

    public Location(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    protected Location(Parcel in) {
        lat = in.readString();
        lng = in.readString();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lat);
        dest.writeString(lng);

    }

}
