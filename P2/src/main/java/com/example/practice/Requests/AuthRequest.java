package com.example.practice.Requests;

import java.io.Serializable;

public class AuthRequest implements Serializable {
    String clubName;
    String password;
    String error;
    boolean authenticated;


    public AuthRequest(String clubName, String password) {
        this.clubName = clubName;
        this.password = password;
    }
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
    public String getClubName() {
        return clubName;
    }
    public String getPassword() {
        return password;
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
}
