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
 * Created by puneet mangla on 19-01-2018.
 */

public class CustomAdapter2 extends ArrayAdapter<NewsFeed > {
    CustomAdapter2(Context context, ArrayList<NewsFeed> combine)
    {
        super(context, R.layout.custom_row2,combine);
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.custom_row2,parent,false);

        NewsFeed news= getItem(position);


        customView.setBackgroundColor(Color.WHITE);
        TextView heading,User;
        heading=(TextView)customView.findViewById(R.id.textView17);
        User=(TextView)customView.findViewById(R.id.user_name);


        heading.setText(news.getHeading());

        User.setText(news.getUser());
        return customView;
    }
}

