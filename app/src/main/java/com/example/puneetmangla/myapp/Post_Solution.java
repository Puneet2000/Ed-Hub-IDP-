package com.example.puneetmangla.myapp;

import android.content.Context;
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
 * Created by puneet mangla on 19-12-2017.
 */

public class Post_Solution extends AsyncTask<String,Void,String> {
    Context context;
    public Post_Solution(Context ctx ) {
        context=ctx;
    }
    @Override
    protected String doInBackground(String... voids) {
        String pbname=voids[0];
        String username=voids[2];
        String soln=voids[1];



        String login_url= null;
        try {
            login_url = "http://192.168.137.1/postsolution.php?pbname="+ URLEncoder.encode(pbname)+"&soln="+URLEncoder.encode(soln,"UTF-8")+"&username="+username;
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
        Toast.makeText(context,result,Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
