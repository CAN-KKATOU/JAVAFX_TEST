package com.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CSSDemo extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../resource/dialog2.fxml"));
            root.lookup("#username").getStyleClass().add("highlight");
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/com/css/scene1.css");
            stage.setScene(scene);
            stage.setTitle("CSSDemo");
            stage.show();
        } catch (IOException e)
        {
            e.printStackTrace();
            Platform.exit();
        }
    }
}
