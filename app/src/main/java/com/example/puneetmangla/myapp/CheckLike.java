package com.example.puneetmangla.myapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by puneet mangla on 20-12-2017.
 */

public class CheckLike extends AsyncTask<String,Void,String> {
    Context context;
    public CheckLike(Context ctx ) {
        context=ctx;
    }
    String pbname="";
    String solutionid="";
    String soltext="";
    String solnby="";
    String username="";
    @Override
    protected String doInBackground(String... voids) {
        pbname=voids[0];
        solutionid=voids[1];
        soltext=voids[2];
        solnby=voids[3];
        username=voids[4];


        String login_url= null;
        login_url = "http://192.168.137.1/checklike.php?username="+username+"&solutionid="+solutionid;

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
        Intent intent = new Intent(context,solutiontext.class);
        intent.putExtra("pbname",pbname);
        intent.putExtra("solutionid",solutionid);
        intent.putExtra("solution",soltext);
        intent.putExtra("solnby",solnby);
        intent.putExtra("username",username);
        intent.putExtra("result",result);
        context.startActivity(intent);

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
