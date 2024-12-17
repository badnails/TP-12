package com.example.practice.Requests;

import com.example.practice.Database.player;

import java.io.Serializable;

public class TransferTicket implements Serializable {
    boolean success;
    player playerObject;
    int mode;

    public TransferTicket(player playerObject, int mode) {
        this.playerObject = playerObject;
        this.mode = mode;
    }

    public player getPlayerObject() {
        return playerObject;
    }
    public void setPlayerObject(player playerObject) {
        this.playerObject = playerObject;
    }
    public int getMode() {
        return mode;
    }
    public boolean getSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
