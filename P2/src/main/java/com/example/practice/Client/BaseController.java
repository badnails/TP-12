package com.example.practice.Client;

import com.example.practice.Requests.EndSession;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class BaseController implements Initializable {
    @FXML
    private ImageView BASE_LOGO;
    @FXML
    private Text BASE_CLUBNAME;
    @FXML
    private Button BASE_LOGOUT, BASE_HOME, BASE_MANAGE, BASE_TRANSFER;
    @FXML
    private AnchorPane BASE_CANVAS;

    public void editCanvas(String page) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(page)));
        AnchorPane toShow = loader.load();
        Object controller = loader.getController();


        BASE_CANVAS.getChildren().remove(1);
        BASE_CANVAS.getChildren().add(toShow);
        AnchorPane.setTopAnchor(toShow, 10.0);
        AnchorPane.setBottomAnchor(toShow, 10.0);
        AnchorPane.setRightAnchor(toShow, 10.0);

        if(controller instanceof PlayerSearchMenuController) {
            ((PlayerSearchMenuController) controller).requestSearch(0);
        }
        else if(controller instanceof TransferMenuController) {
            ((TransferMenuController) controller).requestSearch(0);
        }
    }

    public void showHome(ActionEvent event) throws IOException {
        resetAllButtons();
        AnchorPane.setRightAnchor((Button)event.getSource(), 0.0);
        editCanvas("HomePage.fxml");
    }

    public void showManager(ActionEvent event) throws IOException {
        resetAllButtons();
        AnchorPane.setRightAnchor((Button)event.getSource(), 0.0);
        editCanvas("SearchMenu.fxml");
    }

    public void showMarket(ActionEvent event) throws IOException {
        resetAllButtons();
        AnchorPane.setRightAnchor((Button)event.getSource(), 0.0);
        editCanvas("TransferMenu.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BASE_LOGO.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo/"+ Client.clubObject.getLogoSrc()))));
        BASE_CLUBNAME.setText(Client.clubObject.getName());
    }

    private void resetAllButtons() {
        AnchorPane.setRightAnchor(BASE_HOME, 10.0);
        AnchorPane.setRightAnchor(BASE_MANAGE, 10.0);
        AnchorPane.setRightAnchor(BASE_TRANSFER, 10.0);

    }

    public void logoutUser(ActionEvent event) throws IOException {
        if(Client.connected)
        {
            Client.socket.write(new EndSession(true));
        }
        Client.mainMenu();
    }
}
