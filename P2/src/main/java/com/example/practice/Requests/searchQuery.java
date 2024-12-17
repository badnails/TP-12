package com.example.practice.Requests;

import com.example.practice.Database.player;

import java.io.Serializable;
import java.util.ArrayList;

public class searchQuery implements Serializable {
    String from;
    String name;
    String country;
    Integer age;
    String age_op;
    Integer lsal;
    Integer rsal;
    String position;
    Integer jersey;
    Double height;
    String height_op;
    ArrayList<player> results;
    boolean found;
    int queryType;


    public searchQuery(String name, String country, Integer age, String age_op, Integer lsal, Integer rsal,
                       String position, Integer jersey, Double height, String height_op, int queryType) {
        this.name = name;
        this.country = country;
        this.age = age;
        this.age_op = age_op;
        this.lsal = lsal;
        this.rsal = rsal;
        this.position = position;
        this.jersey = jersey;
        this.height = height;
        this.height_op = height_op;
        results = new ArrayList<>();
        found = false;
        this.queryType = queryType;
    }

    public String getAge_op() {
        return age_op;
    }

    public Double getHeight() {
        return height;
    }

    public String getHeight_op() {
        return height_op;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getLowSalary() {
        return lsal;
    }

    public Integer getHighSalary() {return rsal;}

    public String getPosition() {
        return position;
    }

    public Integer getJersey() {
        return jersey;
    }

    public ArrayList<player> getResults() {
        return results;
    }
    public boolean isFound() {
        return found;
    }
    public void setFound(boolean found) {
        this.found = found;
    }
    public void setResults(ArrayList<player> results) {
        this.results = new ArrayList<>(results);
    }
    public int getQueryType() {
        return queryType;
    }
    public void setQueryType(int queryType) {
        this.queryType = queryType;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
}
