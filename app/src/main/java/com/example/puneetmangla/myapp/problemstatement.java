package com.example.puneetmangla.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class problemstatement extends AppCompatActivity {

    TextView name,tag,text;

    Button post,view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String pbname= getIntent().getExtras().getString("pbname");
        String pbtag=getIntent().getExtras().getString("pbtag");
        String  pbtext=getIntent().getExtras().getString("pbtext");
        String pbuser=getIntent().getExtras().getString("pbUser");
        String username=getIntent().getExtras().getString("username");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problemstatement);
        name=(TextView)findViewById(R.id.name1);
        tag=(TextView)findViewById(R.id.name2);
        text=(TextView) findViewById(R.id.solstatement);
        post=(Button)findViewById(R.id.postsoln);
        view=(Button)findViewById(R.id.viewsol);
        name.setText(pbname);
        tag.setText(pbtag);
        text.setText(pbtext);

    }

    public void Postsoln(View view) {
        Intent intent = new Intent(this,Postsolution.class);
        intent.putExtra("username",getIntent().getExtras().getString("username"));
        intent.putExtra("problemname",getIntent().getExtras().getString("pbname"));
        startActivity(intent);
    }

    public void Viewsoln(View view) {
        DownloadFile1 downloadFile = new DownloadFile1(this);
        downloadFile.execute("http://192.168.137.1/viewsolution.php?pbname="+getIntent().getExtras().getString("pbname"),getIntent().getExtras().getString("username"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.Home:
            {
                Intent intent = new Intent(this,home.class);
                intent.putExtra("username",getIntent().getExtras().getString("username"));
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
