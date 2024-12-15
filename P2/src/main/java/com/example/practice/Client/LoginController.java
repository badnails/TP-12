package com.example.practice.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private MenuItem m11;
    @FXML
    private MenuItem m12;
    @FXML
    private MenuItem m13;
    @FXML
    private MenuItem m21;
    @FXML
    private MenuItem m22;
    @FXML
    private Button m4;

    @FXML
    protected void playerNameSearch(ActionEvent event) throws IOException
    {
        Scene scene = new Scene(FXMLLoader.load(Client.class.getResource("PlayerSearchMenu.fxml")));
        Client.sceneSetter(scene, "Player Search Menu");
    }

    public void backtoMain(ActionEvent event) {
    }

    public void clubCountrySearch(ActionEvent event) {
    }

    public void totalSalaryClub(ActionEvent event) {
    }

    public void exitSystem(ActionEvent event) {
        
    }
}