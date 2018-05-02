package com.example.puneetmangla.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;

public class Adduser extends AppCompatActivity {
EditText users;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);
        users=(EditText)findViewById(R.id.editText2);
        btn=(Button)findViewById(R.id.button4);
    }

    public void filter(View view) {
        String s=users.getText().toString();
        JSONArray jsonArray=new JSONArray();
        String r="";
        int len=s.length();
        for(int i=0;i<len;i++)
        {
            if(s.charAt(i)!='\n')
            {
                r=r+s.charAt(i);
            }
            else
            {
                jsonArray.put(r);
                r="";
            }
            jsonArray.put(r);
        }
        DownloadFile downloadFile = new DownloadFile((Adduser.this));
        downloadFile.execute("http://192.168.137.1/filterbyuser.php?user="+jsonArray.toString(),getIntent().getExtras().getString("username"));
    }
}
