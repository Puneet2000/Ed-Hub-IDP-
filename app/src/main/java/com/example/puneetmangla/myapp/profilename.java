package com.example.puneetmangla.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class profilename extends AppCompatActivity {
EditText editText;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilename);
        editText=(EditText)findViewById(R.id.editText);
        btn=(Button)findViewById(R.id.button5);

    }

    public void Viewprofile(View view) {
        Intent intent=new Intent(this,Viewprofile.class);
        intent.putExtra("username",getIntent().getExtras().getString("username"));
        intent.putExtra("otheruser",editText.getText().toString());
        startActivity(intent);
    }
}
