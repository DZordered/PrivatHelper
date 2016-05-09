package com.example.denis.privathelper.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class GeometryData {
    public final List<Result> results;

    public GeometryData(List<Result> results) {
        this.results = results;
    }
}
