package com.example.puneetmangla.myapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by puneet mangla on 20-12-2017.
 */

public class SetLike extends AsyncTask<String,Void,String> {
    Context context;
    public SetLike(Context ctx ) {
        context=ctx;
    }

    String solutionid="";

    String username="";
    String ischecked="";
    @Override
    protected String doInBackground(String... voids) {

        solutionid=voids[0];

        username=voids[1];
        ischecked=voids[2];

        String login_url= null;
        login_url = "http://192.168.137.1/setlike.php?username="+username+"&solutionid="+solutionid+"&ischecked="+ischecked;

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
        if(ischecked.equals("Check"))
            Toast.makeText(context,"You Liked one Solution",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context,"You Unliked one Solution",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
