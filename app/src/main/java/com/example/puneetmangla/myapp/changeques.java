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

public class changeques extends AppCompatActivity {
EditText ques,ans;
Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeques);
        ques=(EditText)findViewById(R.id.ques);
        ans=(EditText)findViewById(R.id.ans);
        btn=(Button)findViewById(R.id.button3);
    }

    public void SetQues(View view) {
        String question=ques.getText().toString();
        String answer=ans.getText().toString();
        if(question.equals("")|| answer.equals(""))
        {
            Toast.makeText(changeques.this,"empty field",Toast.LENGTH_LONG).show();
        }
        else
        {
            new ChangeQues(getIntent().getExtras().getString("username"),question,answer).execute();
        }
    }
    private class ChangeQues extends AsyncTask<String,Void,String>
    {String user_name="", quest="", answ="";
        ChangeQues(String user_name,String quest,String answ )
        {
            this.user_name=user_name;
            this.answ=answ;
            this.quest=quest;
        }
        @Override
        protected String doInBackground(String... strings) {
            String login_url="http://192.168.137.1/resetques.php?username="+user_name+"&question="+quest+"&answer="+answ;

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
            if(result.equals("Ques Changed"))
            {
                Toast.makeText(changeques.this,result,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(changeques.this,home.class);
                intent.putExtra("username",getIntent().getExtras().getString("username"));
                startActivity(intent);
            }
            else
            {
                Toast.makeText(changeques.this,"Wrong Credentials",Toast.LENGTH_LONG).show();
                ques.setText("");
                ans.setText("");
            }
        }
}}
