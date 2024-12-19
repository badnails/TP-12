package com.example.practice.Requests;

import com.example.practice.Database.club;

import java.io.Serializable;

public class AuthRequest implements Serializable {
    String credName;
    String credPass;
    club clubObject;
    String error;
    boolean authenticated;


    public AuthRequest(String clubName, String password) {
        this.credName = clubName;
        this.credPass = password;
    }
    public String getCredName() {
        return credName;
    }
    public String getCredPass() {
        return credPass;
    }
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
    public boolean isAuthenticated() {
        return authenticated;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getError() {
        return error;
    }
    public void setClubObject(club clubObject) {
        this.clubObject = clubObject;
    }
    public club getClubObject() {
        return clubObject;
    }
}
