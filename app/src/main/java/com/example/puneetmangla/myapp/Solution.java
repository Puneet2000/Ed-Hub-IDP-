package com.example.puneetmangla.myapp;

/**
 * Created by puneet mangla on 19-12-2017.
 */

public class Solution {
    String solID;
    String pbName;
    String soln;
    String solnby;
    String likes;
    public Solution(String id,String name, String text,String user,String likes ) {
        this.pbName=name;

        this.soln=text;
        this.solnby=user;
        this.solID=id;
        this.likes=likes;
    }
    public String getID() {
        return solID;
    }
    public String getName() {
        return pbName;
    }

    public String getLike() {
        return likes;
    }

    public String getText() {
        return soln;
    }

    public String getUser() {
        return solnby;
    }
}
