package com.example.puneetmangla.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Newslist extends AppCompatActivity {

    ListView ls;
BoomMenuButton boomMenuButton;

    ArrayList<NewsFeed> combine=new ArrayList<NewsFeed>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newslist);
        ls=(ListView)findViewById(R.id.news);
        boomMenuButton=(BoomMenuButton)findViewById(R.id.bmb9);
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
        String result=getIntent().getExtras().getString("JSON2");
        JSONArray arr = null;
        try {
            arr = new JSONObject(result.toString()).getJSONArray("posts");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // get the 'posts' section from the JSON string
        for (int i = 0; i < arr.length(); i++) {
            String Heading="",Text="",Username="";

            JSONObject post = null;
            try {
                post = arr.getJSONObject(i).getJSONObject("post");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                Heading=(post.getString("Heading"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                Text=(post.getString("Feed"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                Username=(post.getString("Username"));
            } catch (JSONException e) {
                e.printStackTrace();
            }


            NewsFeed newsfeed=new NewsFeed(Heading,Username,Text);
            combine.add(newsfeed);
        }

        ListAdapter listAdapter=new CustomAdapter2(this,combine);
        ls.setAdapter(listAdapter);
        ls.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView <?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getApplicationContext(),viewnews.class);
                        intent.putExtra("heading",combine.get(i).getHeading());
                        intent.putExtra("user",combine.get(i).getUser());
                        intent.putExtra("text",combine.get(i).getText());

                        startActivity(intent);


                    }
                }
        );



    }
}
