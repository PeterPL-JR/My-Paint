package com.peterpl.mypaint;

import com.peterpl.mypaint.file.*;
import com.peterpl.mypaint.gui.*;
import com.peterpl.mypaint.lang.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class MyPaint extends Application {
    public static final String TITLE = "MyPaint";

    private Scene scene;
    private BorderPane container;

    private void initContainer() {
        LanguageManager.init();
        initMenu();
    }

    private void initMenu() {
        MainMenu menu = new MainMenu();

        menu.addGroup("file")
                .addItem("new", () -> {})
                .addItem("open", () -> {})
                .addSeparator()
                .addItem("save", () -> {})
                .addItem("save_as", () -> {})
                .addItem("save_all", () -> {})
                .addSeparator()
                .addItem("close", () -> {})
                .addSeparator()
                .addItem("exit", this::exit);
        menu.addGroup("edit")
                .addItem("undo", () -> {})
                .addItem("redo", () -> {})
                .addSeparator()
                .addItem("cut", () -> {})
                .addItem("copy", () -> {})
                .addItem("paste", () -> {});
        menu.addGroup("view")
                .addItem("zoom_in", () -> {})
                .addItem("zoom_out", () -> {});

        container.setTop(menu);
    }

    @Override
    public void start(Stage stage) {
        container = new BorderPane();
        initContainer();
        scene = new Scene(container);

        stage.setTitle(TITLE);
        stage.getIcons().add(ResourceManager.loadImage("icon", 32, 32));
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public void exit() {
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}