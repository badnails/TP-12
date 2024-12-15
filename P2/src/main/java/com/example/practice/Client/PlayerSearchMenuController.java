package com.example.practice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.example.practice.Database.player;
import com.example.practice.Database.searchQuery;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class PlayerSearchMenuController {

    @FXML
    private Label TEXT_SEARCH_FOUND_NUMBER;
    @FXML
    private Button BUTTON_SEARCH, BACK_TO_MAIN;
    @FXML
    private TextField TEXTBOX_SEARCH_NAME, TEXTBOX_SEARCH_AGE, TEXTBOX_SEARCH_COUNTRY, TEXTBOX_SEARCH_JN, TEXTBOX_SEARCH_SALARY, TEXTBOX_SEARCH_POS;
    @FXML
    private ListView<AnchorPane> psl;


    private ArrayList<player> searchForPlayers()
    {
        searchQuery query = new searchQuery(
                TEXTBOX_SEARCH_NAME.getText().isEmpty()?null:TEXTBOX_SEARCH_NAME.getText(),
                TEXTBOX_SEARCH_COUNTRY.getText().isEmpty()?null:TEXTBOX_SEARCH_COUNTRY.getText(),
                TEXTBOX_SEARCH_AGE.getText().isEmpty()?null:parseInput(TEXTBOX_SEARCH_AGE.getText()),
                TEXTBOX_SEARCH_SALARY.getText().isEmpty()?null:parseInput(TEXTBOX_SEARCH_SALARY.getText()),
                TEXTBOX_SEARCH_POS.getText().isEmpty()?null:TEXTBOX_SEARCH_POS.getText(),
                TEXTBOX_SEARCH_JN.getText().isEmpty()?null:parseInput(TEXTBOX_SEARCH_JN.getText())
        );

        return Client.db.searchFunction(query);
    }


    public void searchPlayer(ActionEvent event) throws IOException {
        psl.getItems().clear();
        ArrayList<player> found = searchForPlayers();
        TEXT_SEARCH_FOUND_NUMBER.setText(String.format("Found: %d Players", found.size()));

        if (!found.isEmpty()) {

            for(player playerObject: found)
            {
                AnchorPane Tile = (AnchorPane) FXMLLoader.load(Objects.requireNonNull(getClass().getResource("playerTile.fxml")));
                Label[] labels = new Label[8];

                for(int j = 0; j < 7; j++)
                {
                    labels[j] = (Label) ((HBox) ((VBox) Tile.getChildren().get(0)).getChildren().get(j)).getChildren().get(1);
                }

                labels[0].setText(playerObject.getName());
                labels[1].setText(playerObject.getCountry());
                labels[2].setText(playerObject.getClub());
                labels[3].setText(String.valueOf(playerObject.getAge()));
                labels[4].setText(String.format("%.2f", playerObject.getHeight()));
                labels[5].setText(String.valueOf(playerObject.getSalary()));
                labels[6].setText(String.valueOf(playerObject.getJersey()));

                psl.getItems().add(Tile);
            }
        }
        else
        {
            Label label = new Label("No Player Found");
            VBox vBox = new VBox(label);

            vBox.setAlignment(Pos.CENTER);
            AnchorPane anchorPane = new AnchorPane(vBox);

            AnchorPane.setTopAnchor(vBox, 0.0);
            AnchorPane.setBottomAnchor(vBox, 0.0);
            AnchorPane.setLeftAnchor(vBox, 0.0);
            AnchorPane.setRightAnchor(vBox, 0.0);
            psl.getItems().add(anchorPane);
        }
    }

    public void backToMain(ActionEvent event) throws IOException {
        Client.mainMenu();
    }

    private Integer parseInput(String text) {
        try
        {
            return Integer.parseInt(text);
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }
}
