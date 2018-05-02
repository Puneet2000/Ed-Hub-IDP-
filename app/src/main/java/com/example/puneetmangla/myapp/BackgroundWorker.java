package com.example.puneetmangla.myapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 * Created by puneet mangla on 10-12-2017.
 */

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;

    BackgroundWorker(Context ctx)
    {
        context=  ctx;
    }
    String username="1";
    String result="1";
    String type="1";
    @Override
    protected String doInBackground(String... voids) {
        type=voids[0];



        if(type.equals("login"))
        {username=voids[1];
            String password=voids[2];

            String login_url="http://192.168.137.1/login.php?username="+username+"&password="+password;

            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();


                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();




                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                result="";

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
        }
        else if (type.equals("register"))
        { String branch=voids[1];
        String year=voids[2];
        String roll=voids[3];
        String password=voids[4];
        String name=voids[5];
        String ques=voids[6];
        String ans=voids[7];
        username=branch+year+"BTECH110"+roll;

            String login_url="http://192.168.137.1/registerpage.php?branch="+branch+"&year="+year+"&rollnumber="+roll+"&password="+password+"&Name="+name+"&question="+ques+"&answer="+ans;
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();

                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                result="";


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
        }
        return null;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {

        if(type.equals("login")&& result.equals("login successful"))
        {
            Toast.makeText(context,"Successfully Loged In",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context,home.class);
            intent.putExtra("username",username);
            context.startActivity(intent);
        }
        else if(type.equals("login")&& result.equals("login Unsuccessful"))
        {
            Toast.makeText(context,"Wrong Username/Password \n Please try again",Toast.LENGTH_SHORT).show();
        }
        else if(type.equals("register")&& result.equals("Success"))
        {
            Toast.makeText(context,"Welcome User",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context,home.class);
            intent.putExtra("username",username);
            context.startActivity(intent);
        }
        else if(type.equals("register")&& result.equals("Username doesn't/already exists"))
        {
            Toast.makeText(context,result,Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
