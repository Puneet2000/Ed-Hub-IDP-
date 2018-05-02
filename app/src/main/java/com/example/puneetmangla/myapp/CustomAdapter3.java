package com.example.puneetmangla.myapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by puneet mangla on 25-01-2018.
 */

public class CustomAdapter3 extends ArrayAdapter<Links>{
    CustomAdapter3(Context context, ArrayList<Links> combine)
    {
        super(context, R.layout.custom_row3,combine);
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.custom_row3,parent,false);

        Links link= getItem(position);


        customView.setBackgroundColor(Color.WHITE);
        TextView heading,User,linktext;
        heading=(TextView)customView.findViewById(R.id.textView23);
        User=(TextView)customView.findViewById(R.id.textView24);
        linktext=(TextView)customView.findViewById(R.id.textView25);


        heading.setText(link.getPurpose());

        User.setText(link.getUser());
        linktext.setText(link.getText());
        return customView;
    }
}
