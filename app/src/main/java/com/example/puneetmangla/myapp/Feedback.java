package com.example.puneetmangla.myapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Feedback extends AppCompatActivity {
 EditText editText;
 Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        editText=(EditText)findViewById(R.id.editText3);
        button=(Button)findViewById(R.id.button7);
    }

    public void GiveFeed(View view) {
        new FeedBack(editText.getText().toString()).execute();

    }
    private class FeedBack extends AsyncTask<String,Void,String>
    {
String feed="";
FeedBack(String feed)
{
    this.feed=feed;
}
        @Override
        protected String doInBackground(String... strings) {
            String login_url="http://192.168.137.1/feedback.php?feedback="+feed;

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
            Toast.makeText(Feedback.this,result,Toast.LENGTH_LONG).show();
            startActivity(new Intent(Feedback.this,MainActivity.class));
        }
    }
}
