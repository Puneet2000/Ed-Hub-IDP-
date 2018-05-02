package com.example.puneetmangla.myapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 * Created by puneet mangla on 15-12-2017.
 */

class Post_Problem extends AsyncTask<String,Void,String> {
    Context context;
    public Post_Problem(Context ctx ) {
        context=ctx;
    }
    @Override
    protected String doInBackground(String... voids) {
        String pbname=voids[0];
        String username=voids[3];
        String pbtag=voids[1];
        String pbtext=voids[2];


        String login_url= null;
        try {
            login_url = "http://192.168.137.1/postproblem.php?pbname="+ URLEncoder.encode(pbname)+"&pbtag="+pbtag+"&pbtext="+URLEncoder.encode(pbtext,"UTF-8")+"&username="+username;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

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
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context,result,Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
