package com.example.puneetmangla.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class viewnews extends AppCompatActivity {
 TextView heading,user,feed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewnews);
        heading=(TextView)findViewById(R.id.textView19);
        user=(TextView)findViewById(R.id.textView20);
        feed=(TextView)findViewById(R.id.textView21);
        heading.setText(getIntent().getExtras().getString("heading"));
        user.setText(getIntent().getExtras().getString("user"));
        feed.setText(getIntent().getExtras().getString("text"));

    }
}
