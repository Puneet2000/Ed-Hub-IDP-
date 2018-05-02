package com.example.puneetmangla.myapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class solutiontext extends AppCompatActivity {
    TextView name,user,text;

    FloatingActionButton bad,satisfy,happy,veryhappy,best;
    String username;
    String solby;
    String solutionid;
    BoomMenuButton boomMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solutiontext);
        String pbname= getIntent().getExtras().getString("pbname");

        String  soltext=getIntent().getExtras().getString("solution");
        username=getIntent().getExtras().getString("username");
        solby=getIntent().getExtras().getString("solnby");
        solutionid=getIntent().getExtras().getString("solutionid");
        name=(TextView)findViewById(R.id.pbname);
        user=(TextView)findViewById(R.id.solnby);
        text=(TextView) findViewById(R.id.solstatement);
        bad=(FloatingActionButton)findViewById(R.id.sad);
        satisfy=(FloatingActionButton)findViewById(R.id.satisfy);
        happy=(FloatingActionButton)findViewById(R.id.happy);
        veryhappy=(FloatingActionButton)findViewById(R.id.veryhappy);
        best=(FloatingActionButton)findViewById(R.id.best);



        name.setText(pbname);
        user.setText("by "+solby);
        text.setText(soltext);
        boomMenuButton=(BoomMenuButton)findViewById(R.id.bmb8);
        boomMenuButton.setPiecePlaceEnum(PiecePlaceEnum.DOT_9_1);
        boomMenuButton.setButtonPlaceEnum(ButtonPlaceEnum.SC_9_1);
        TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder().normalText("Home").normalImageRes(R.drawable.homeicon).listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                Intent intent = new Intent(getApplicationContext(),home.class);
                intent.putExtra("username", getIntent().getExtras().getString("username"));
                startActivity(intent);


            }
        });

        boomMenuButton.addBuilder(builder);
        TextOutsideCircleButton.Builder builder1 = new TextOutsideCircleButton.Builder().normalText("Post").normalImageRes(R.drawable.uploadicon).listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                Intent intent = new Intent(getApplicationContext(),postproblem.class);
                intent.putExtra("username", getIntent().getExtras().getString("username"));
                startActivity(intent);
            }
        });

        boomMenuButton.addBuilder(builder1);


        TextOutsideCircleButton.Builder builder3 = new TextOutsideCircleButton.Builder().normalText("All Problems").normalImageRes(R.drawable.view).listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                DownloadFile downloadFile = new DownloadFile(getApplicationContext());
                downloadFile.execute("http://192.168.137.1/viewproblem.php",getIntent().getExtras().getString("username"));

            }
        });

        boomMenuButton.addBuilder(builder3);
        TextOutsideCircleButton.Builder builder4 = new TextOutsideCircleButton.Builder().normalText("Settings").normalImageRes(R.drawable.settingsicon).listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                Intent intent = new Intent(getApplicationContext(),settings.class);
                intent.putExtra("username", getIntent().getExtras().getString("username"));
                startActivity(intent);

            }
        });

        boomMenuButton.addBuilder(builder4);
        TextOutsideCircleButton.Builder builder5 = new TextOutsideCircleButton.Builder().normalText("Logout").normalImageRes(R.drawable.logouticon).listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        boomMenuButton.addBuilder(builder5);
        TextOutsideCircleButton.Builder builder6 = new TextOutsideCircleButton.Builder().normalText("Filters").normalImageRes(R.drawable.filter2).listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                Intent intent = new Intent(getApplicationContext(),filterproblem.class);
                intent.putExtra("username", getIntent().getExtras().getString("username"));
                startActivity(intent);

            }
        });

        boomMenuButton.addBuilder(builder6);
        TextOutsideCircleButton.Builder builder7 = new TextOutsideCircleButton.Builder().normalText("Search People").normalImageRes(R.drawable.viewprofile).listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                Intent intent = new Intent(getApplicationContext(),profilename.class);
                intent.putExtra("username", getIntent().getExtras().getString("username"));
                startActivity(intent);

            }
        });

        boomMenuButton.addBuilder(builder7);
        TextOutsideCircleButton.Builder builder8 = new TextOutsideCircleButton.Builder().normalText("News").normalImageRes(R.drawable.newsfeed).listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                DownloadFile2 downloadFile = new DownloadFile2(getApplicationContext());
                downloadFile.execute("http://192.168.137.1/getfeed.php",getIntent().getExtras().getString("username"));

            }
        });

        boomMenuButton.addBuilder(builder8);
        TextOutsideCircleButton.Builder builder9 = new TextOutsideCircleButton.Builder().normalText("PostFeed").normalImageRes(R.drawable.postfeed).listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                Intent intent = new Intent(getApplicationContext(),Postfeed.class);
                intent.putExtra("username", getIntent().getExtras().getString("username"));
                startActivity(intent);

            }
        });

        boomMenuButton.addBuilder(builder9);
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

    public void Sad(View view) {

       Rate rate=new  Rate("1",username,solby,solutionid,view);
       rate.execute();



    }

    public void Satisfy(View view) {

        Rate rate=new  Rate("2",username,solby,solutionid,view);
        rate.execute();




    }

    public void Happy(View view) {

        Rate rate=new  Rate("3",username,solby,solutionid,view);
        rate.execute();



    }

    public void Veryhappy(View view) {

        Rate rate=new  Rate("4",username,solby,solutionid,view);
        rate.execute();




    }

    public void Best(View view) {

        Rate rate=new  Rate("5",username,solby,solutionid,view);
        rate.execute();



    }

    private class Rate extends AsyncTask<String,Void,String>
    {
        String point="",user_name="",solutionby="",solID="";
        View view;
  Rate(String point,String user_name,String solutionby,String solID,View view)
  {
      this.point=point;
      this.user_name=user_name;
      this.solutionby=solutionby;
      this.solID=solID;
      this.view=view;
  }



        @Override
        protected String doInBackground(String... strings) {
                String login_url = "http://192.168.137.1/ratings.php?username="+user_name+"&point="+point+"&solnby="+solutionby+"&solnid="+solID;


            try {
                URL url =new URL(login_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.connect();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                byte [] read=new byte[1000];
                int reader =inputStream.read(read);
                String result=new String(read,0,reader);
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

            if(result=="same")
            {

                view.setEnabled(false);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Successfully rated",Toast.LENGTH_LONG).show();
            }

        }
    }
}
