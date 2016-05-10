package com.example.denis.privathelper.pojos;


import android.os.Parcel;
import android.os.Parcelable;

public class AtmDevice implements Parcelable {

    public final String cityRU;
    public final String fullAddressRu;
    public final String latitude;
    public final String longitude;

    public AtmDevice(String cityRU, String fullAddressRU, String latitude, String longitude) {
        this.cityRU = cityRU;
        this.fullAddressRu = fullAddressRU;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected AtmDevice(Parcel in) {
        cityRU = in.readString();
        fullAddressRu = in.readString();
        latitude = in.readString();
        longitude = in.readString();
    }

    public static final Creator<AtmDevice> CREATOR = new Creator<AtmDevice>() {
        @Override
        public AtmDevice createFromParcel(Parcel in) {
            return new AtmDevice(in);
        }

        @Override
        public AtmDevice[] newArray(int size) {
            return new AtmDevice[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cityRU);
        dest.writeString(fullAddressRu);
        dest.writeString(latitude);
        dest.writeString(longitude);
    }
}
