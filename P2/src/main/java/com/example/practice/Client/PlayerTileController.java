package com.example.practice.Client;

import com.example.practice.Database.player;
import com.example.practice.Requests.TransferTicket;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class PlayerTileController {

    public void transferPlayer(ActionEvent event) {
        try
        {
            Button caller = (Button)event.getSource();
            player forTransfer = (player) caller.getUserData();
            System.out.println(forTransfer.getName()+" transfer listed");
            caller.setDisable(true);

            TransferTicket openTicket = new TransferTicket(forTransfer, 1);
            Client.socket.write(openTicket);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
