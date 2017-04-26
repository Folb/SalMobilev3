package com.example.folb.salmobile.models;

import java.util.ArrayList;

/**
 * Created by Follo on 26.04.2017.
 */

public class Location {

    private String Company;
    private String name;
    private int pens;

    public Location(String company, String name, int pens) {
        Company = company;
        this.name = name;
        this.pens = pens;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPens() {
        return pens;
    }

    public void setPens(int pens) {
        this.pens = pens;
    }
}
