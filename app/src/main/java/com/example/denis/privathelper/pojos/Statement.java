package com.example.denis.privathelper.pojos;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Statement implements Parcelable {

    public final String name;
    public final String email;
    public final String address;
    public final String phone;
    public final String index;
    public final String state;
    public final String country;

    public Statement(String name, String email, String phone, String address, String country
    , String state, String index) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.country = country;
        this.state = state;
        this.index = index;
    }

    protected Statement(Parcel in) {
        name = in.readString();
        email = in.readString();
        address = in.readString();
        phone = in.readString();
        country = in.readString();
        state = in.readString();
        index = in.readString();
    }

    public static final Creator<Statement> CREATOR = new Creator<Statement>() {
        @Override
        public Statement createFromParcel(Parcel in) {
            return new Statement(in);
        }

        @Override
        public Statement[] newArray(int size) {
            return new Statement[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(address);
        dest.writeString(country);
        dest.writeString(state);
        dest.writeString(index);
    }
}
