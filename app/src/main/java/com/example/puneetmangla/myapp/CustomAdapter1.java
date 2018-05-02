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
 * Created by puneet mangla on 19-12-2017.
 */

public class CustomAdapter1 extends ArrayAdapter<Solution > {
    CustomAdapter1(Context context, ArrayList<Solution> combine)
    {
        super(context, R.layout.custom_row1,combine);
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.custom_row1,parent,false);

        Solution sol=getItem(position);

            customView.setBackgroundColor(Color.WHITE);




        TextView Name,User,Likes;
        Name=(TextView)customView.findViewById(R.id.textView4);
        User=(TextView)customView.findViewById(R.id.solnby);
        Likes=(TextView)customView.findViewById(R.id.likes);

        Name.setText("      "+sol.getName());
        Likes.setText("         Likes - "+sol.getLike());
        User.setText("          by "+sol.getUser());
        return customView;
    }
}
