package com.example.puneetmangla.myapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class changebio extends AppCompatActivity {
EditText biotext;
Button change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changebio);
        biotext=(EditText)findViewById(R.id.biotext);
        change=(Button)findViewById(R.id.changebio);
        new GetBio(getIntent().getExtras().getString("username")).execute();
    }

    public void Change(View view) {
        new ChangeBio(getIntent().getExtras().getString("username"),biotext.getText().toString()).execute();

    }
    private class ChangeBio extends AsyncTask<String,Void,String>
    {  String user_name="",bio="";
        ChangeBio(String user_name,String bio)
        {
          this.user_name=user_name;
          this.bio=bio;
        }

        @Override
        protected String doInBackground(String... strings) {
            String login_url="http://192.168.137.1/resetbio.php?username="+user_name+"&biography="+bio;

            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();


                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();




                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));


                byte[] buffer = new byte[10000]; //adjust size depending on how much data you are expecting
                int readBytes = inputStream.read(buffer);

                String result = new String(buffer, 0, readBytes);

                inputStream.close();


                httpURLConnection.disconnect();

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Intent intent = new Intent(changebio.this,home.class);
            intent.putExtra("username",getIntent().getExtras().getString("username"));
            startActivity(intent);
        }
    }
        private class GetBio extends AsyncTask<String,Void,String>
        { String user_name;
        GetBio(String user_name)
        {
            this.user_name=user_name;
        }

            @Override
            protected String doInBackground(String... strings) {
                String login_url="http://192.168.137.1/getbio.php?username="+user_name;

                try {
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();


                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.connect();




                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));


                    byte[] buffer = new byte[10000]; //adjust size depending on how much data you are expecting
                    int readBytes = inputStream.read(buffer);

                    String result = new String(buffer, 0, readBytes);

                    inputStream.close();


                    httpURLConnection.disconnect();

                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                String Bio = "";
                JSONArray arr = null;
                try {
                    arr = new JSONObject(result.toString()).getJSONArray("posts");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                    for (int i = 0; i < arr.length(); i++) {


                        JSONObject post = null;
                        try {
                            post = arr.getJSONObject(i).getJSONObject("post");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            Bio = (post.getString("Biography"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                    biotext.setText(Bio);
                }
            }
    }
