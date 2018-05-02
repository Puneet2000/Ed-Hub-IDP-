package com.example.puneetmangla.myapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.nightonke.boommenu.BoomMenuButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class viewlinks extends AppCompatActivity {
    ListView ls;


    ArrayList<Links> combine=new ArrayList<Links>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewlinks);
        ls=(ListView)findViewById(R.id.links);

        String result=getIntent().getExtras().getString("JSON3");
        JSONArray arr = null;
        try {
            arr = new JSONObject(result.toString()).getJSONArray("posts");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // get the 'posts' section from the JSON string
        for (int i = 0; i < arr.length(); i++) {
            String Heading="",Text="",Username="";

            JSONObject post = null;
            try {
                post = arr.getJSONObject(i).getJSONObject("post");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                Heading=(post.getString("Purpose"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                Text=(post.getString("Link"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                Username=(post.getString("Username"));
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Links link=new Links(Heading,Username,Text);
            combine.add(link);
        }

        ListAdapter listAdapter=new CustomAdapter3(this,combine);
        ls.setAdapter(listAdapter);
        ls.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView <?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(combine.get(i).getText()));


                        startActivity(intent);


                    }
                }
        );



    }

    public void addlink(View view) {
        Intent intent = new Intent(getApplicationContext(),addlink.class);
        intent.putExtra("username", getIntent().getExtras().getString("username"));
        startActivity(intent);
    }
}

