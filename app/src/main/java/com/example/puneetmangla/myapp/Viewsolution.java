package com.example.puneetmangla.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class Viewsolution extends AppCompatActivity {
    TextView pb;
    ListView ls;
    ArrayList<Solution> combine=new ArrayList<Solution>();
    BoomMenuButton boomMenuButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewsolution);
        String username=getIntent().getExtras().getString("username");
        pb=(TextView)findViewById(R.id.textView2);

        ls=(ListView)findViewById(R.id.list1);
        boomMenuButton=(BoomMenuButton)findViewById(R.id.bmb6);
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
        String result=getIntent().getExtras().getString("JSON1");
        JSONArray arr = null;
        try {
            arr = new JSONObject(result).getJSONArray("posts");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // get the 'posts' section from the JSON string
        for (int i = 0; i < arr.length(); i++) {
            String ProblemName="",Solution="",Username="";
            String SolutionID="",Likes="";

            JSONObject post = null;
            try {
                post = arr.getJSONObject(i).getJSONObject("post");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                SolutionID=(post.getString("SolutionID"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                ProblemName=(post.getString("ProblemName"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                Solution=(post.getString("Solution"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                Username=(post.getString("Username"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                Likes=(post.getString("Likes"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Solution sol=new Solution(SolutionID,ProblemName,Solution,Username,Likes);
            combine.add(sol);
        }
        pb.setText("Solutions : "+combine.get(0).getName());
        ListAdapter listAdapter=new CustomAdapter1(this,combine);
        ls.setAdapter(listAdapter);
        ls.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView <?> adapterView, View view, int i, long l) {



                        Intent intent = new Intent(Viewsolution.this,solutiontext.class);
                        intent.putExtra("pbname",combine.get(i).getName());
                        intent.putExtra("solutionid",combine.get(i).getID());
                        intent.putExtra("solution",combine.get(i).getText());
                        intent.putExtra("solnby",combine.get(i).getUser());
                        intent.putExtra("username",getIntent().getExtras().getString("username"));

                     startActivity(intent);
                    }
                }
        );
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
