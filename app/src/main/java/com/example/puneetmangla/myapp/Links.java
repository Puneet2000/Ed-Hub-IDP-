package com.example.puneetmangla.myapp;

/**
 * Created by puneet mangla on 25-01-2018.
 */

public class Links {
    String purpose;
    String user;
    String text;

    public Links(String purpose,String user,String text ) {
        this.purpose=purpose;
        this.user=user;
        this.text=text;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }
}
