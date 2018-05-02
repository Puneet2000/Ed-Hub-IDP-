package com.example.puneetmangla.myapp;

/**
 * Created by puneet mangla on 19-01-2018.
 */

public class NewsFeed {
    String heading;
    String user;
    String text;

    public NewsFeed(String heading,String user,String text ) {
     this.heading=heading;
     this.user=user;
     this.text=text;
    }

    public String getHeading() {
        return heading;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }


}
