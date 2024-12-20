package com.example.practice.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.example.practice.Database.player;
import com.example.practice.Requests.searchQuery;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class PlayerSearchMenuController implements Initializable {
    @FXML
    private ToggleButton TOGGLE_AGE_G, TOGGLE_AGE_L, TOGGLE_AGE_E, TOGGLE_H_G, TOGGLE_H_L, TOGGLE_H_E;
    @FXML
    private Label TEXT_SEARCH_FOUND_NUMBER;
    @FXML
    private Button BUTTON_SEARCH, BACK_TO_MAIN, BUTTON_TILE_TRANSFER, BUTTON_RESET;
    @FXML
    private TextField TEXTBOX_SEARCH_NAME, TEXTBOX_SEARCH_AGE, TEXTBOX_SEARCH_COUNTRY, TEXTBOX_SEARCH_JN, TEXTBOX_SEARCH_POS, TEXTBOX_SEARCH_HEIGHT, TEXTBOX_SEARCH_LSAL, TEXTBOX_SEARCH_RSAL;
    @FXML
    private ListView<AnchorPane> psl;

    ToggleGroup group1 = new ToggleGroup();
    ToggleGroup group2 = new ToggleGroup();

    public void searchPlayer(ActionEvent event) throws IOException {
        requestSearch(1);
    }

    public void requestSearch(int mode)             //(1) Regular (0) Previous
    {
        String age_op;
        age_op = ((ToggleButton)group1.getSelectedToggle()).getText();
        String height_op;
        height_op = ((ToggleButton)group2.getSelectedToggle()).getText();
        searchQuery query;

        if(mode==1)
        {
             query = new searchQuery(
                    TEXTBOX_SEARCH_NAME.getText().isEmpty()?null:TEXTBOX_SEARCH_NAME.getText(),
                    TEXTBOX_SEARCH_COUNTRY.getText().isEmpty()?null:TEXTBOX_SEARCH_COUNTRY.getText(),
                    TEXTBOX_SEARCH_AGE.getText().isEmpty()?null:(Integer) parseInput(TEXTBOX_SEARCH_AGE.getText()),
                    age_op,
                    TEXTBOX_SEARCH_LSAL.getText().isEmpty()?null:(Integer) parseInput(TEXTBOX_SEARCH_LSAL.getText()),
                    TEXTBOX_SEARCH_RSAL.getText().isEmpty()?null:(Integer) parseInput(TEXTBOX_SEARCH_RSAL.getText()),
                    TEXTBOX_SEARCH_POS.getText().isEmpty()?null:TEXTBOX_SEARCH_POS.getText(),
                    TEXTBOX_SEARCH_JN.getText().isEmpty()?null:(Integer) parseInput(TEXTBOX_SEARCH_JN.getText()),
                    TEXTBOX_SEARCH_HEIGHT.getText().isEmpty()?null:(Double) parseInput(TEXTBOX_SEARCH_HEIGHT.getText()),
                    height_op,
                    1//Club Search
            );
            Client.lastSearchQ = query;
        }
        else
        {
            query = Client.lastSearchQ;
        }

        try {
            Client.socket.reset();
            Client.socket.write(query);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void listFiller(ArrayList<player> found) throws IOException {
        psl.getItems().clear();
        TEXT_SEARCH_FOUND_NUMBER.setText(String.format("Found: %d Players", found.size()));

        if (!found.isEmpty()) {

            for(player playerObject: found)
            {
                AnchorPane Tile = (AnchorPane) FXMLLoader.load(Objects.requireNonNull(getClass().getResource("playerTile.fxml")));
                Label[] labels = new Label[8];

                for(int j = 0; j < 8; j++)
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
                labels[7].setText(String.valueOf(playerObject.getPosition()));

                Button TRANSFER_BUTTON = (Button) Tile.getChildren().get(1);

                if(playerObject.isTransferListed())
                {
                    TRANSFER_BUTTON.setDisable(true);
                }
                else
                {
                    TRANSFER_BUTTON.setUserData(playerObject);
                }


                Tile.prefWidthProperty().bind(psl.widthProperty().subtract(30));
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

    private Number parseInput(String text) {
        try
        {
            return Integer.parseInt(text);
        }
        catch (NumberFormatException e)
        {
            try
            {
                return Double.parseDouble(text);
            }
            catch (NumberFormatException e1)
            {
                return -1;
            }
        }
    }


    public void resetFields(ActionEvent event) {
        TEXTBOX_SEARCH_NAME.clear();
        TEXTBOX_SEARCH_AGE.clear();
        TEXTBOX_SEARCH_COUNTRY.clear();
        TEXTBOX_SEARCH_JN.clear();
        TEXTBOX_SEARCH_LSAL.clear();
        TEXTBOX_SEARCH_RSAL.clear();
        TEXTBOX_SEARCH_POS.clear();
        TEXTBOX_SEARCH_HEIGHT.clear();
        TOGGLE_H_G.setSelected(true);
        TOGGLE_AGE_G.setSelected(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TOGGLE_AGE_E.setToggleGroup(group1);
        TOGGLE_AGE_L.setToggleGroup(group1);
        TOGGLE_AGE_G.setToggleGroup(group1);
        TOGGLE_H_E.setToggleGroup(group2);
        TOGGLE_H_L.setToggleGroup(group2);
        TOGGLE_H_G.setToggleGroup(group2);
        TOGGLE_H_G.setSelected(true);
        TOGGLE_AGE_G.setSelected(true);
        Client.controller = this;
    }
}
