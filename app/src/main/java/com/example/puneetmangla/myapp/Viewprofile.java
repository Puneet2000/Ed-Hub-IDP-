package com.example.puneetmangla.myapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

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

import de.hdodenhof.circleimageview.CircleImageView;

public class Viewprofile extends AppCompatActivity {
    BoomMenuButton boomMenuButton;
    TextView username,bio;
    RatingBar ratingBar;
    CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewprofile);
        Getinfo getinfo = new Getinfo(this);

        username=(TextView)findViewById(R.id.otextView1);
        bio=(TextView)findViewById(R.id.obiotext);
        ratingBar=(RatingBar)findViewById(R.id.orating);
        circleImageView=(CircleImageView)findViewById(R.id.oprofile_image);

        username.setText(getIntent().getExtras().getString("username"));
        getinfo.execute(getIntent().getExtras().getString("otheruser"));

        boomMenuButton=(BoomMenuButton)findViewById(R.id.obmb);
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
    private class Getinfo extends AsyncTask<String, Void, String> {

        Context context;

        public Getinfo(Context ctx) {
            context = ctx;
        }

        @Override
        protected String doInBackground(String... voids) {

            try {
                URL url = new URL("http://192.168.137.1/getinfo.php?username=" + voids[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));


                byte[] buffer = new byte[10000000]; //adjust size depending on how much data you are expecting
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
            if (result.equals("None")) {
                Toast.makeText(Viewprofile.this, "Username doesnt have account", Toast.LENGTH_LONG).show();
                username.setText("None");
                bio.setText("This Username doesn't have a account");
                float x=0;
                ratingBar.setRating(x);
            } else {
                String Biography = "", Rating = "", name = "";
                JSONArray arr = null;
                try {
                    arr = new JSONObject(result.toString()).getJSONArray("posts");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // get the 'posts' section from the JSON string
                for (int i = 0; i < arr.length(); i++) {


                    JSONObject post = null;
                    try {
                        post = arr.getJSONObject(i).getJSONObject("post");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        Biography = (post.getString("Biography"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        Rating = (post.getString("Rating"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        name = (post.getString("Name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                username.setText(name);
                bio.setText(Biography);
                ratingBar.setRating(Float.parseFloat(Rating));


            }}

            @Override
            protected void onPreExecute () {
                super.onPreExecute();
            }

            @Override
            protected void onProgressUpdate (Void...values){
                super.onProgressUpdate(values);
            }
        }
    }

