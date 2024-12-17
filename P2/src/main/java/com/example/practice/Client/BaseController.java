package com.example.practice.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BaseController implements Initializable {
    @FXML
    private Text BASE_CLUBNAME;
    @FXML
    private Button BASE_LOGOUT, BASE_HOME, BASE_MANAGE, BASE_TRANSFER;
    @FXML
    private AnchorPane BASE_CANVAS;




    public void showHome(ActionEvent event) throws IOException {
        AnchorPane home = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        BASE_CANVAS.getChildren().remove(1);
        BASE_CANVAS.getChildren().add(home);
        AnchorPane.setTopAnchor(home, 10.0);
        AnchorPane.setBottomAnchor(home, 10.0);
        AnchorPane.setRightAnchor(home, 10.0);

    }

    public void showManager(ActionEvent event) throws IOException {
        AnchorPane searchMenu = FXMLLoader.load(getClass().getResource("SearchMenu.fxml"));
        BASE_CANVAS.getChildren().remove(1);
        BASE_CANVAS.getChildren().add(searchMenu);
        AnchorPane.setTopAnchor(searchMenu, 10.0);
        AnchorPane.setBottomAnchor(searchMenu, 10.0);
        AnchorPane.setRightAnchor(searchMenu, 10.0);
    }

    public void showMarket(ActionEvent event) throws IOException {
        AnchorPane tr = FXMLLoader.load(getClass().getResource("TransferMenu.fxml"));
        BASE_CANVAS.getChildren().remove(1);
        BASE_CANVAS.getChildren().add(tr);
        AnchorPane.setTopAnchor(tr, 10.0);
        AnchorPane.setBottomAnchor(tr, 10.0);
        AnchorPane.setRightAnchor(tr, 10.0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BASE_CLUBNAME.setText(Client.clubName);
    }
}
