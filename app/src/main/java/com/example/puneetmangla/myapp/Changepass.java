package com.example.puneetmangla.myapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class Changepass extends AppCompatActivity {
EditText pass,pass1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        pass=(EditText)findViewById(R.id.npass);
        pass1=(EditText)findViewById(R.id.npass1);
    }

    public void Set(View view) {
        String a=pass.getText().toString();
        String b=pass1.getText().toString();
        if(!a.equals(b))
            Toast.makeText(Changepass.this,"Password didn't match",Toast.LENGTH_LONG).show();
        else
        {
            new SetPass(getIntent().getExtras().getString("username"),a).execute();
        }
    }
    private class SetPass extends AsyncTask<String,Void,String>
    { String username="",pass="";
SetPass(String username,String pass)
{
    this.username=username;
    this.pass=pass;
}
        @Override
        protected String doInBackground(String... strings) {
            String login_url="http://192.168.137.1/changepass.php?username="+username+"&password="+pass;

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
            Toast.makeText(Changepass.this,"Password changed succesfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(Changepass.this,loginpage.class));
        }
    }
}
