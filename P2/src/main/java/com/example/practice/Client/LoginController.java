package com.example.practice.Client;

import com.example.practice.Requests.AuthRequest;
import com.example.practice.Server.ClientSocketHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button LOGIN_SIGN;
    @FXML
    private TextField LOGIN_CLUBNAME;
    @FXML
    private TextField LOGIN_PASS;


    public void clientSignIn(ActionEvent event) {
        try
        {
            if(LOGIN_CLUBNAME.getText().isEmpty() || LOGIN_PASS.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Credentials");
                alert.setHeaderText(null);
                alert.setContentText("Please enter valid credentials");
                alert.showAndWait();
            }
            else
            {
                AuthRequest authRequest = new AuthRequest(LOGIN_CLUBNAME.getText(), LOGIN_PASS.getText());
                ClientSocketHandler socket = new ClientSocketHandler("127.0.0.1", 11111);
                socket.write(authRequest);
                try
                {
                    authRequest = (AuthRequest) socket.read();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                if(authRequest instanceof AuthRequest)
                {
                    if(authRequest.isAuthenticated())
                    {
                        Client.socket = socket;
                        Client.clubObject = authRequest.getClubObject();
                        AnchorPane a1 = FXMLLoader.load(getClass().getResource("Base.fxml"));
                        AnchorPane canvas = (AnchorPane) ((VBox)a1.getChildren().get(0)).getChildren().get(1);
                        AnchorPane home = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                        canvas.getChildren().add(home);
                        AnchorPane.setTopAnchor(home, 10.0);
                        AnchorPane.setBottomAnchor(home, 10.0);
                        AnchorPane.setRightAnchor(home, 10.0);

                        Scene scene = new Scene(a1);

                        Client.sceneSetter(scene, authRequest.getCredName());
                        new ClientListener();
                        Client.connected = true;
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Credentials");
                        alert.setHeaderText(null);
                        alert.setContentText(authRequest.getError());
                        alert.showAndWait();
                        Client.mainMenu();
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}