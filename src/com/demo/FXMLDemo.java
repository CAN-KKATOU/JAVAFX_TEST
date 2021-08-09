package com.demo;

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class FXMLDemo extends Application implements Initializable {
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Button okButton;
    @FXML private Button cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        okButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Verifying " + username.getText() + ":" + password.getText());
            alert.showAndWait(); });
        cancelButton.setOnAction(event -> {
            username.setText("");
            password.setText(""); });
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../resource/dialog.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("FXMLDemo");
            primaryStage.show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
