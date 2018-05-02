package com.example.puneetmangla.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import org.json.JSONArray;
import org.json.JSONException;

public class AddTag extends AppCompatActivity {
CheckBox check1,check2,check3,check4,check5,check6,check7,check8,check9,check10,check11;
Button btn;
String array="";
BoomMenuButton boomMenuButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tag);
        check1=(CheckBox)findViewById(R.id.checkBox11);
        check2=(CheckBox)findViewById(R.id.checkBox5);
        check3=(CheckBox)findViewById(R.id.checkBox6);
        check4=(CheckBox)findViewById(R.id.checkBox7);
        check5=(CheckBox)findViewById(R.id.checkBox8);
        check6=(CheckBox)findViewById(R.id.checkBox9);
        check7=(CheckBox)findViewById(R.id.checkBox10);
        check8=(CheckBox)findViewById(R.id.checkBox);
        check9=(CheckBox)findViewById(R.id.checkBox2);
        check10=(CheckBox)findViewById(R.id.checkBox3);
        check11=(CheckBox)findViewById(R.id.checkBox4);

        btn=(Button)findViewById(R.id.button6);
        boomMenuButton=(BoomMenuButton)findViewById(R.id.bmb11);
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

    public void Display(View view) {
        JSONArray json=new JSONArray();
        if(check1.isChecked()) {
            json.put("Computer Science");
        }
        if(check2.isChecked()) {
            json.put("Maths");
        }
        if(check3.isChecked()) {
            json.put("Chemistry");
        }
        if(check4.isChecked()) {
            json.put("Physics");
        }
        if(check5.isChecked()) {
            json.put("Programming");
        }
        if(check6.isChecked()) {
            json.put("Development");
        }
        if(check7.isChecked()) {
            json.put("Biology");
        }
        if(check8.isChecked()) {
            json.put("Electrical");
        }
        if(check9.isChecked()) {
            json.put("Logics");
        }
        if(check10.isChecked()) {
            json.put("Data Structures");
        }
        if(check11.isChecked()) {
            json.put("General knowledge");
        }
       array=json.toString();
        DownloadFile downloadFile=new DownloadFile(AddTag.this);
        downloadFile.execute("http://192.168.137.1/filterbytag.php?tag="+array,getIntent().getExtras().getString("username"));
    }

}
