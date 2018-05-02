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
 * Created by puneet mangla on 19-12-2017.
 */

public class DownloadFile1 extends AsyncTask<String,Void,String> {
    public String username="";
    Context context;
    public DownloadFile1(Context ctx) {
        context=ctx;
    }
    @Override
    protected String doInBackground(String... voids) {




        String login_url=voids[0];
        username=voids[1];

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
        if(result.equals("None"))
        {
            Toast.makeText(context,"No solutions yet",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context,home.class);

            intent.putExtra("username",username);
            context.startActivity(intent);

        }
        else {
            Intent intent = new Intent(context, Viewsolution.class);
            intent.putExtra("JSON1", result);
            intent.putExtra("username", username);
            context.startActivity(intent);
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
