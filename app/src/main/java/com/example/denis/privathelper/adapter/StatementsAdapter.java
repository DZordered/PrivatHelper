package com.example.denis.privathelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.denis.privathelper.R;
import com.example.denis.privathelper.pojos.Statement;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;

import java.util.ArrayList;


public class StatementsAdapter extends ArrayAdapter<Statement> {
    private int resId;
    private ArrayList<Statement> statements;

    public StatementsAdapter(Context context, int resourceId, ArrayList<Statement> statements) {
        super(context, resourceId, statements);
        this.resId = resourceId;
        this.statements = statements;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View newView = convertView;
        Statement statement = getItem(position);

        if(newView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resId,parent, false);
        }

        TextView nameView = (TextView) convertView.findViewById(R.id.itemNameView);
        TextView addressView = (TextView) convertView.findViewById(R.id.itemAddressView);
        TextView phoneView = (TextView) convertView.findViewById(R.id.itemPhoneView);
        TextView emailView = (TextView) convertView.findViewById(R.id.itemEmailView);

        nameView.setText(statement.name);
        addressView.setText(statement.address);
        phoneView.setText(statement.phone);
        emailView.setText(statement.email);
        return convertView;
    }
}
