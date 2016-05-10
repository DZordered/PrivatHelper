package com.example.denis.privathelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.denis.privathelper.R;
import com.example.denis.privathelper.pojos.AtmDevice;

import java.util.ArrayList;


public class AtmAdapter extends ArrayAdapter<AtmDevice> {

    private ArrayList<AtmDevice> devices;
    private int resId;

    public AtmAdapter(Context context, int resource, ArrayList<AtmDevice> devices) {
        super(context, resource, devices);
        this.devices = devices;
        this.resId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View newView = convertView;
        AtmDevice atmDevice = getItem(position);

        if(newView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resId, parent, false);
        }
        TextView cityRuAtm = (TextView) convertView.findViewById(R.id.cityRuAtmView);
        TextView addressRuAtmView = (TextView) convertView.findViewById(R.id.addressRuAtmView);
        TextView latitudeAtm = (TextView) convertView.findViewById(R.id.latitudeAtmView);
        TextView longtitudeAtm = (TextView) convertView.findViewById(R.id.longtitudeAtmView);

        cityRuAtm.setText(atmDevice.cityRU);
        addressRuAtmView.setText(atmDevice.fullAddressRu);
        latitudeAtm.setText(atmDevice.latitude);
        longtitudeAtm.setText(atmDevice.longitude);

        return convertView;
    }
}
