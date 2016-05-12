package com.example.denis.privathelper.pojos;

import android.os.Parcel;
import android.os.Parcelable;


public class TerminalDevice implements Parcelable {
    public final String type;
    public final String cityRU;
    public final String fullAddressRu;
    public final String latitude;
    public final String longitude;

    public TerminalDevice(String type, String cityRU, String fullAddressRu, String latitude, String longitude) {
        this.type = type;
        this.cityRU = cityRU;
        this.fullAddressRu = fullAddressRu;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected TerminalDevice(Parcel in) {
        type = in.readString();
        cityRU = in.readString();
        fullAddressRu = in.readString();
        latitude = in.readString();
        longitude = in.readString();
    }

    public static final Creator<TerminalDevice> CREATOR = new Creator<TerminalDevice>() {
        @Override
        public TerminalDevice createFromParcel(Parcel in) {
            return new TerminalDevice(in);
        }

        @Override
        public TerminalDevice[] newArray(int size) {
            return new TerminalDevice[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(cityRU);
        dest.writeString(fullAddressRu);
        dest.writeString(latitude);
        dest.writeString(longitude);
    }
}
