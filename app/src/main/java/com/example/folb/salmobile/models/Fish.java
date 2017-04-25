package com.example.folb.salmobile.models;

import android.util.Log;

import com.example.folb.salmobile.supportClasses.Support;

import java.io.Serializable;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

/**
 * Created by folb on 29.03.17.
 */

public class Fish implements Serializable{

    private int id;
    private HashMap<Integer, Integer> lice;
    private boolean bucket;

    public Fish(int id) {
        this.id = id;
        lice = new HashMap<>();
        addAllLiceTypes();
    }

    public boolean isBucket() {
        return bucket;
    }

    public void setBucket(boolean bucket) {
        this.bucket = bucket;
    }

    private void addAllLiceTypes() {
        for (Integer s : Support.getAllLiceTypes()) {
            lice.put(s, 0);
        }
    }

    public void addToType(int type) {
        lice.put(type, lice.get(type) + 1);
    }

    public void removeFromType(int type) {
        if (lice.get(type) > 0) {
            lice.put(type, lice.get(type) - 1);
        }
    }

    public int getId() {
        return id;
    }

    public HashMap<Integer, Integer> getLice() {
        return lice;
    }
}
