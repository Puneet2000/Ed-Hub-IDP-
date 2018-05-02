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

public class Editpass extends AppCompatActivity {
EditText old,new1,new2;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpass);
        old=(EditText)findViewById(R.id.old);
        new1=(EditText)findViewById(R.id.new1);
        new2=(EditText)findViewById(R.id.new2);
        btn= (Button) findViewById(R.id.set);
    }

    public void SetPass(View view) {
        String oldpass=old.getText().toString();
        String newpass=new1.getText().toString();
        String confpass=new2.getText().toString();
        if(!newpass.equals(confpass))
        {
            Toast.makeText(Editpass.this,"Password didnt match",Toast.LENGTH_LONG).show();
            old.setText("");
            new1.setText("");
            new2.setText("");
        }
        else
        {
            new ChangePassword(getIntent().getExtras().getString("username"),oldpass,confpass).execute();
        }
    }
    private class ChangePassword extends AsyncTask<String,Void,String>
    {String user_name="",oldpassword="",newpassword="";
    ChangePassword(String user_name,String oldpassword,String newpassword)
        {
            this.user_name=user_name;
            this.newpassword=newpassword;
            this.oldpassword=oldpassword;
        }

        @Override
        protected String doInBackground(String... strings) {
            String login_url="http://192.168.137.1/resetpass.php?username="+user_name+"&opassword="+oldpassword+"&npassword="+newpassword;

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
           if(result.equals("Password Changed"))
            {
                Toast.makeText(Editpass.this,result,Toast.LENGTH_LONG).show();
                startActivity(new Intent(Editpass.this,loginpage.class));
            }
            else
            {
                Toast.makeText(Editpass.this,"Not changed",Toast.LENGTH_LONG).show();
                old.setText("");
                new1.setText("");
                new2.setText("");
            }
        }
    }
}
