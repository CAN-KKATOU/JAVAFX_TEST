package com.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChoiceTest extends Application {
    private static final double rem = new Text("").getLayoutBounds().getHeight();

    private static HBox hBox(Node... children)
    {
        HBox box = new HBox(0.8 * rem, children);
        box.setPadding(new Insets(0.8 * rem));
        return box;
    }

    @Override
    public void start(Stage primaryStage) {
        Label sampleText = new Label("The quick brown fox jumps over the lazy dog.");
        sampleText.setPrefWidth(40 * rem);
        sampleText.setPrefHeight(5 * rem);
        sampleText.setFont(Font.font(14));

        CheckBox bold = new CheckBox("Bold");
        CheckBox italic = new CheckBox("Italic");

        RadioButton small = new RadioButton("Small");
        RadioButton medium = new RadioButton("Medium");
        RadioButton large = new RadioButton("Large");
        RadioButton extraLarge = new RadioButton("Extra Large");

        small.setUserData(8);
        medium.setUserData(14);
        large.setUserData(18);
        extraLarge.setUserData(36);

        ToggleGroup group = new ToggleGroup();
        small.setToggleGroup(group);
        medium.setToggleGroup(group);
        large.setToggleGroup(group);
        extraLarge.setToggleGroup(group);
        medium.setSelected(true);

        ComboBox<String> families = new ComboBox<>();
        families.getItems().addAll(Font.getFamilies());
        families.setValue("System");

        EventHandler<ActionEvent> listener = event -> {
            int size = (int) group.getSelectedToggle().getUserData();
            Font font = Font.font(
                    families.getValue(),
                    bold.isSelected() ? FontWeight.BOLD : FontWeight.NORMAL,
                    italic.isSelected() ? FontPosture.ITALIC : FontPosture.REGULAR,
                    size
            );
            sampleText.setFont(font);
        };

        small.setOnAction(listener);
        medium.setOnAction(listener);
        large.setOnAction(listener);
        extraLarge.setOnAction(listener);
        bold.setOnAction(listener);
        italic.setOnAction(listener);
        families.setOnAction(listener);

        VBox root = new VBox(0.8 * rem,
                hBox(sampleText),
                hBox(bold, italic),
                hBox(small, medium, large, extraLarge),
                hBox(families));

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("ChoiceTest");
        primaryStage.show();
    }
}
