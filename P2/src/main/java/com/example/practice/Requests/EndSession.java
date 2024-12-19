package com.example.practice.Requests;

import java.io.Serializable;

public class EndSession implements Serializable {
    boolean logout;
    public EndSession(boolean logout) {
        this.logout = logout;
    }
    public boolean isLogout() {
        return logout;
    }
}
