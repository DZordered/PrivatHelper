package com.example.denis.privathelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.denis.privathelper.R;
import com.example.denis.privathelper.pojos.AtmDevice;
import com.example.denis.privathelper.pojos.TerminalDevice;

import java.util.ArrayList;


public class TerminalsAdapter extends ArrayAdapter<TerminalDevice> {

    private int resId;

    private ArrayList<TerminalDevice> devices;

    public TerminalsAdapter(Context context, int resource, ArrayList<TerminalDevice> devices) {
        super(context, resource, devices);
        this.resId = resource;
        this.devices = devices;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View newView = convertView;

        TerminalDevice terminalDevice = getItem(position);

        if(newView == null)  newView = LayoutInflater.from(getContext()).inflate(resId,parent, false);

        TextView terminalCity = (TextView) newView.findViewById(R.id.terminalCity);
        TextView terminalType = (TextView) newView.findViewById(R.id.terminalType);
        TextView terminalFullAddress = (TextView) newView.findViewById(R.id.terminalFullAddress);
        TextView terminalLatitude = (TextView) newView.findViewById(R.id.terminalLatitude);
        TextView terminalLongitude = (TextView) newView.findViewById(R.id.terminalLongitude);

        terminalCity.setText(terminalDevice.cityRU);
        terminalType.setText(terminalDevice.type);
        terminalFullAddress.setText(terminalDevice.fullAddressRu);
        terminalLatitude.setText(terminalDevice.latitude);
        terminalLongitude.setText(terminalDevice.longitude);

        return newView;
    }
}
