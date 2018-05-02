package com.example.puneetmangla.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginpage extends AppCompatActivity {
    EditText username,user_pass;
    Button lgn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        username=(EditText)findViewById(R.id.username1);
        user_pass=(EditText)findViewById(R.id.password1);
        lgn=(Button)findViewById(R.id.login2);


    }



    public void OnLogin(View view) {
        String user_name=username.getText().toString();
        String password=user_pass.getText().toString();
        String type="login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,user_name,password);
    }


    public void Forgot(View view) {
Intent intent = new Intent(this,forgotPass.class);
startActivity(intent);
    }
}

