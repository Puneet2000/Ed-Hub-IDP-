package com.example.puneetmangla.myapp;

/**
 * Created by puneet mangla on 18-12-2017.
 */

public class Problem {
    String pbID;
    String pbName;
    String pbTag;
    String pbText;
    String pbUser;
    public Problem(String id,String name,String tag, String text,String user ) {
        this.pbName=name;
        this.pbTag=tag;
        this.pbText=text;
        this.pbUser=user;
        this.pbID=id;
    }
    public String getID() {
        return pbID;
    }
    public String getName() {
        return pbName;
    }

    public String getTag() {
        return pbTag;
    }

    public String getText() {
        return pbText;
    }

    public String getUser() {
        return pbUser;
    }
}
