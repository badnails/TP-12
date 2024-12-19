package com.example.practice.Client;

import com.example.practice.Database.player;
import com.example.practice.Requests.TransferTicket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.IOException;

public class TransferTileController {

    public void buyPlayer(ActionEvent event) throws IOException{
        Button button = (Button) event.getSource();
        player toBuy = (player) button.getUserData();

        TransferTicket openTicket = new TransferTicket(toBuy, 2);
        Client.socket.reset();
        Client.socket.write(openTicket);
    }
}
