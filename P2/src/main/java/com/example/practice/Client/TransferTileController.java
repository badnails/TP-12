package com.example.practice.Client;

import com.example.practice.Database.player;
import com.example.practice.Requests.TransferTicket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.IOException;

public class TransferTileController {

    @FXML
    private Button BUTTON_TILE_BUY;
    private TransferMenuController transferMenuController;

    void setTransferMenuController(TransferMenuController transferMenuController) {
        this.transferMenuController = transferMenuController;
    }

    public void buyPlayer(ActionEvent event) throws IOException, ClassNotFoundException {
        Button button = (Button) event.getSource();
        player toBuy = (player) button.getUserData();

        TransferTicket openTicket = new TransferTicket(toBuy, 2);
        Client.socket.write(openTicket);
        TransferTicket response = (TransferTicket) Client.socket.read();
        if(response.getSuccess())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText(toBuy.getName()+" has been purchased!");
            alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(toBuy.getName()+" could not be purchased!");
            alert.showAndWait();
        }

    }
}
