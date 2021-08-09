package com.demo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * @version 1.0
 * @author Gaatou Can
 */
public class MenuTest extends Application {
    private final TextArea textArea = new TextArea();

    /**
     * Makes this item ir, if a menu, its descendant items, carry out the
     * given action if there isn't already an action defined.
     * @param item the menu item (which may be a menu)
     * @param action the default action
     */
    private void defaultAction(MenuItem item, EventHandler<ActionEvent> action)
    {
        if (item instanceof Menu)
            for (MenuItem child : ((Menu) item).getItems())
                defaultAction(child, action);
        else if (item.getOnAction() == null)
            item.setOnAction(action);
    }

    @Override
    public void start(Stage stage) {
        Menu fileMenu = new Menu("File");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(event -> Platform.exit());

        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        openItem.setAccelerator(KeyCombination.keyCombination("Shortcut + O"));
        MenuItem saveItem = new MenuItem("Save");
        saveItem.setAccelerator(KeyCombination.keyCombination("Shortcut + S"));
        MenuItem saveAsItem = new MenuItem("Save ax ...");

        fileMenu.getItems().addAll(newItem, openItem, saveItem, saveAsItem, new SeparatorMenuItem(), exitItem);

        CheckMenuItem readOnlyItem = new CheckMenuItem("Read-only");
        readOnlyItem.setOnAction(event ->
        {
            saveItem.setDisable(readOnlyItem.isSelected());
            saveAsItem.setDisable(readOnlyItem.isSelected());
        });

        ToggleGroup group = new ToggleGroup();
        RadioMenuItem insertItem = new RadioMenuItem("Insert");
        insertItem.setToggleGroup(group);
        insertItem.setSelected(true);
        RadioMenuItem overtypeItem = new RadioMenuItem("Overtype");
        overtypeItem.setToggleGroup(group);

        MenuItem cutItem = new MenuItem("Cut");
        MenuItem copyItem = new MenuItem("Copy");
        MenuItem pasteItem = new MenuItem("Paste");

        ContextMenu contextMenu = new ContextMenu(cutItem, copyItem, pasteItem);
        textArea.setContextMenu(contextMenu);

        Menu editMenu = new Menu("Edit");
        editMenu.getItems().addAll(cutItem, copyItem, pasteItem);

        Menu optionsMenu = new Menu("Options", null, readOnlyItem, insertItem, overtypeItem);
        editMenu.getItems().add(optionsMenu);

        MenuItem aboutProgramItem = new MenuItem("_About this program");
        MenuItem aboutCoreJavaItem = new MenuItem("About _Core Java");
        Menu helpMenu = new Menu("_Help", null, aboutProgramItem, aboutCoreJavaItem);

        MenuBar bar = new MenuBar(fileMenu, editMenu, helpMenu);
        VBox root  = new VBox(bar, textArea);
        for (Menu menu : bar.getMenus())
            defaultAction(menu, event -> {
                MenuItem item = (MenuItem) event.getSource();
                textArea.appendText(item.getText() + " selected\n");
            });

        textArea.setEditable(false);
        stage.setScene(new Scene(root));
        stage.setTitle("MenuTest");
        stage.show();
    }
}
