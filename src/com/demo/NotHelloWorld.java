package com.demo;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NotHelloWorld extends Application {
    private static final int MESSAGE_X = 50;
    private static final int MESSAGE_Y = 100;

    private static final int PREFERRED_WIDTH = 300;
    private static final int PREFERRED_HEIGHT = 200;

    @Override
    public void start(Stage primaryStage) {
        Text message = new Text(MESSAGE_X, MESSAGE_Y, "Not a Hello World programme");
        Font font = Font.font("PingFang SC", FontWeight.NORMAL, FontPosture.ITALIC, 16);
        message.setFont(font);

        Pane root = new Pane(message);
        root.setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("NotHelloWorld");
        primaryStage.show();
    }
}
