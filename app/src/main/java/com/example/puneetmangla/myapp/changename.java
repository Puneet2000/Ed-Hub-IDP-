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

public class changename extends AppCompatActivity {
 EditText oldname,newname;
 Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changename);
        oldname=(EditText)findViewById(R.id.oldname);
        newname=(EditText)findViewById(R.id.newname);
    }

    public void OnChange(View view) {
        String old=oldname.getText().toString();
        String newn=newname.getText().toString();
        if(old.equals("")|| newn.equals(""))
        {
            Toast.makeText(changename.this,"Empty Field",Toast.LENGTH_LONG).show();
        }
        else
        {
            new ChangeName(getIntent().getExtras().getString("username"),old,newn).execute();
        }
    }
    private class ChangeName extends AsyncTask<String,Void,String>
    {String user_name="", oldName="", newName="";
ChangeName(String user_name,String oldName,String newName)
{
    this.user_name=user_name;
    this.newName=newName;
    this.oldName=oldName;
}
        @Override
        protected String doInBackground(String... strings) {
            String login_url="http://192.168.137.1/resetname.php?username="+user_name+"&oldname="+oldName+"&newname="+newName;

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
            if(result.equals("Name Changed"))
            {
                Toast.makeText(changename.this,result,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(changename.this,home.class);
                intent.putExtra("username",getIntent().getExtras().getString("username"));
                startActivity(intent);
            }
            else
            {
                Toast.makeText(changename.this,"Wrong Credentials",Toast.LENGTH_LONG).show();
                oldname.setText("");
                newname.setText("");
            }
        }
    }
}
