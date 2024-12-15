package com.example.practice;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import com.example.practice.Database.DB;

public class Client extends Application {
    public static Stage mainStage;
    protected static DB db;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        mainMenu();
    }

    public static void mainMenu() throws IOException
    {
        Scene scene = new Scene(FXMLLoader.load(Client.class.getResource("MainMenu.fxml")));
        sceneSetter(scene, "Main Menu");
    }
    
    public static void sceneSetter(Scene scene, String title)
    {
        mainStage.setTitle(title);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void main(String[] args) {
        db = new DB();
        db.input();
        launch();
    }
}