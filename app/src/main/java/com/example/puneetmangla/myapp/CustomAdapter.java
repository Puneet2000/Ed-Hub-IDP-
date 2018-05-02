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
 * Created by puneet mangla on 18-12-2017.
 */

public class CustomAdapter extends ArrayAdapter<Problem >{
    CustomAdapter(Context context, ArrayList<Problem> combine)
    {
        super(context, R.layout.custom_row,combine);
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.custom_row,parent,false);

        Problem pb=getItem(position);


    customView.setBackgroundColor(Color.WHITE);



        TextView Name,Tag,User;
        Name=(TextView)customView.findViewById(R.id.problemname);
        Tag=(TextView)customView.findViewById(R.id.problemtag);
        User=(TextView)customView.findViewById(R.id.user);
        Name.setText("      "+pb.getName());
        Tag.setText("     ( "+pb.getTag()+" )");
        User.setText("          by "+pb.getUser());
        return customView;
    }}
