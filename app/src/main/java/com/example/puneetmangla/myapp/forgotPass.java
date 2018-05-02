package com.example.puneetmangla.myapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class forgotPass extends AppCompatActivity {
 EditText user,answer;
 TextView question;
 Button ok,submit;
 String answ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        user=(EditText)findViewById(R.id.user);
        answer=(EditText)findViewById(R.id.answer);
        question=(TextView)findViewById(R.id.question);
        ok=(Button)findViewById(R.id.button2);
        submit=(Button)findViewById(R.id.submit);
    }

    public void Getquestion(View view) {
        if(user.getText().toString().equals(""))
            Toast.makeText(forgotPass.this,"Invalid Username",Toast.LENGTH_LONG).show();
        else
        {
            new GetQues(this).execute(user.getText().toString());
        }

    }

    public void Submit(View view) {
        if(answer.getText().toString().equals(answ))
        { Intent intent=new Intent(this,Changepass.class);
        intent.putExtra("username",user.getText().toString());
   startActivity(intent);
        }
        else
        {
            Toast.makeText(forgotPass.this,"Answer didn't match",Toast.LENGTH_LONG).show();
            user.setText("");
            question.setText("");
            answer.setText("");
        }
    }

    private class GetQues extends AsyncTask<String,Void,String>
    { Context context;
GetQues(Context ctx)
{
    context=ctx;
}
        @Override
        protected String doInBackground(String... strings) {




            String login_url="http://192.168.137.1/getquestion.php?username="+strings[0];

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
    String ques="";
    String ans="";

            JSONArray arr = null;
            try {
                arr = new JSONObject(result.toString()).getJSONArray("posts");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // get the 'posts' section from the JSON string
            if(arr.length()==0)
            {
                Toast.makeText(forgotPass.this,"Invalid Username",Toast.LENGTH_LONG).show();
            }
            else
            { for (int i = 0; i < arr.length(); i++) {


                JSONObject post = null;
                try {
                    post = arr.getJSONObject(i).getJSONObject("post");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    ques = (post.getString("Question"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    ans = (post.getString("Answer"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            question.setText(ques);
            answ=ans;
        }
    }
}}
