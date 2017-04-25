package com.example.folb.salmobile.models;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by folb on 29.03.17.
 */

public class LouseCounting extends ATask {

    private String company;
    private String location;
    private String penNmb;
    private String user;
    private String comment;
    private String timestamp;
    private double temperature;
    private boolean bucketSet;

    private ArrayList<Fish> fishes;

    public LouseCounting(String company, String location, String penNmb, String user, boolean bucketSet) {
        this.company = company;
        this.location = location;
        this.penNmb = penNmb;
        this.user = user;
        this.temperature = -273;
        this.bucketSet = bucketSet;
        //timestamp = new DateTime(DateTime.now()).toString();
        timestamp = "now";
        fishes = new ArrayList<>();
        fishes.add(new Fish(1));
    }

    public void addNewFish() {
        fishes.add(new Fish(fishes.size() + 1));
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public String getPenNmb() {
        return penNmb;
    }

    public String getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public ArrayList<Fish> getFishes() {
        return fishes;
    }

    public boolean isBucketSet() {
        return bucketSet;
    }

    public void setBucketSet(boolean bucketSet) {
        this.bucketSet = bucketSet;
    }
}
