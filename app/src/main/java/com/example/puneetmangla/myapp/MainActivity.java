package com.example.puneetmangla.myapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;


public class MainActivity extends AppCompatActivity {

    BoomMenuButton boomMenuButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        boomMenuButton=(BoomMenuButton)findViewById(R.id.bmb);
        HamButton.Builder builder = new HamButton.Builder()
                .normalImageRes(R.drawable.login)
                .normalTextRes(R.string.Login).shadowEffect(true).subNormalTextRes(R.string.Logintext).listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        Intent intent = new Intent(MainActivity.this,loginpage.class);
                        startActivity(intent);
                    }
                });;

        boomMenuButton.addBuilder(builder);
        HamButton.Builder builder1 = new HamButton.Builder()
                .normalImageRes(R.drawable.register1)
                .normalTextRes(R.string.Register).shadowEffect(true).subNormalTextRes(R.string.Registertext).listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        Intent intent =new Intent(MainActivity.this,registerpage.class);
                        startActivity(intent);
                    }
                });

        boomMenuButton.addBuilder(builder1);
        HamButton.Builder builder2 = new HamButton.Builder()
                .normalImageRes(R.drawable.feedback)
                .normalTextRes(R.string.feedback).shadowEffect(true).subNormalTextRes(R.string.feedbacktext).listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        Intent intent =new Intent(MainActivity.this,Feedback.class);
                        startActivity(intent);
                    }
                });

        boomMenuButton.addBuilder(builder2);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1,menu);

        return super.onCreateOptionsMenu(menu);
    }
}
