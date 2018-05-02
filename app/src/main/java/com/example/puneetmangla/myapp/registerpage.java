package com.example.puneetmangla.myapp;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registerpage extends AppCompatActivity {
    EditText username1,password1,password2,aname,answer;
    Button reg;
    AutoCompleteTextView question;
String [] secQues = {"What is your favourite colour ?", "Which is your favourite book ?","Which is your favourite food ?","Where were you born ?","What is the name of your favorite pet ?","What high school did you attend ?","What is your favorite movie ?","What is your favorite website ?","What is your favorite online platform ?","What is your favorite social media website ?"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpage);

        username1=(EditText)findViewById(R.id.username2);
        password1=(EditText)findViewById(R.id.password2);
        password2=(EditText)findViewById(R.id.password3);
        aname=(EditText)findViewById(R.id.Name);
        question=(AutoCompleteTextView) findViewById(R.id.question);
        answer=(EditText)findViewById(R.id.ans);
        reg=(Button)findViewById(R.id.register2);
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.select_dialog_item,secQues);
question.setThreshold(-1);
question.setAdapter(adapter);
    }

    public void OnReg(View view) {


        String s1 = password1.getText().toString();
        String s2 = password2.getText().toString();
        String s3 = username1.getText().toString();
        String s4 = aname.getText().toString();
        String s5 = question.getText().toString();
        String s6 = answer.getText().toString();
        if (s3.length() < 14) {
            Toast.makeText(getApplicationContext(), "Username doesn't exists/enter your Roll no", Toast.LENGTH_LONG).show();
            password1.setText("");
            password2.setText("");
            username1.setText("");
            aname.setText("");
            question.setText("");
            answer.setText("");
        } else {
            String branch = s3.substring(0, 2);


            String year = s3.substring(2, 4);


            String roll = s3.substring(12);


            if (!s1.equals(s2)) {

                password1.setText("");
                password2.setText("");
                Toast.makeText(registerpage.this, "Passwords didn't match", Toast.LENGTH_SHORT).show();
            } else if (s3.equals("") || s1.equals("") || s2.equals("") || s4.equals("") || s5.equals("") || s6.equals("")) {

                Toast.makeText(registerpage.this, "Empty Field", Toast.LENGTH_SHORT).show();
            } else {
                int flag = 0, flag1 = 0;
                for (int i = 0; i < s1.length(); i++) {
                    if (s1.charAt(i) == ' ') {
                        flag = 1;
                        break;
                    }
                }
                if (s1.charAt(0) == '.' || s1.charAt(0) == '0' || s1.charAt(0) == '1' || s1.charAt(0) == '2' || s1.charAt(0) == '3' || s1.charAt(0) == '4' || s1.charAt(0) == '5' || s1.charAt(0) == '6' || s1.charAt(0) == '7' || s1.charAt(0) == '8' || s1.charAt(0) == '9' || s1.charAt(s1.length() - 1) == '.')
                    flag1 = 1;
                if (flag == 0 && flag1 == 0) {
                    String type = "register";
                    BackgroundWorker backgroundWorker1 = new BackgroundWorker(this);
                    backgroundWorker1.execute(type, branch, year, roll, s1, s4, s5, s6);
                } else {
                    password1.setText("");
                    password2.setText("");
                    Toast.makeText(registerpage.this, "Incorrect Password format", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}


