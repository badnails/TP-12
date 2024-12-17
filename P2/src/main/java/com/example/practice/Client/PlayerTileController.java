package com.example.practice.Client;

import com.example.practice.Database.player;
import com.example.practice.Requests.TransferTicket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class PlayerTileController {

    @FXML
    private Button BUTTON_TILE_TRANSFER;

    public void transferPlayer(ActionEvent event) {
        try
        {
            Button caller = (Button)event.getSource();
            player forTransfer = (player) caller.getUserData();
            System.out.println(forTransfer.getName()+" transfer listed");
            caller.setDisable(true);

            TransferTicket openTicket = new TransferTicket(forTransfer, 1);
            Client.socket.write(openTicket);
            TransferTicket response = (TransferTicket) Client.socket.read();
            if(response.getSuccess())
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText(forTransfer.getName()+" has been added to transfer market");
                alert.showAndWait();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(forTransfer.getName()+" could not be added to transfer market");
                alert.showAndWait();
            }

        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }
}
