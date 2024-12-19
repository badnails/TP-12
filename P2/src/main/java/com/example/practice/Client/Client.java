package com.example.practice.Client;

import com.example.practice.Database.club;
import com.example.practice.Requests.EndSession;
import com.example.practice.Requests.searchQuery;
import com.example.practice.Server.ClientSocketHandler;
import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.practice.Database.DB;

public class Client extends Application {
    static boolean connected = false;
    protected static ClientSocketHandler socket;
    protected static Object controller;
    protected static Stage mainStage;
    protected static club clubObject;
    protected static searchQuery lastSearchQ = new searchQuery(1);
    protected static searchQuery lastTransferQ = new searchQuery(2);

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        mainMenu();
    }

    @Override
    public void stop() throws IOException {
        if(connected)
        {
            socket.write(new EndSession(false));
        }
        System.out.println(clubObject!=null?clubObject.getName():"null" + " ended");
        socket = null;
        clubObject = null;
    }

    public static void mainMenu() throws IOException
    {
        Scene scene = new Scene(FXMLLoader.load(Client.class.getResource("LoginPage.fxml")));
        sceneSetter(scene, "Main Menu");
    }
    
    public static void sceneSetter(Scene scene, String title)
    {
        scene.getStylesheets().add(Objects.requireNonNull(Client.class.getResource("styles.css")).toExternalForm());
        mainStage.setTitle(title);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}