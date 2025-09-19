package com.peterpl.mypaint;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;

public class MyPaint extends Application {
    public static final String TITLE = "MyPaint";

    private Scene scene;
    private StackPane container;

    private void initContainer() {
    }

    @Override
    public void start(Stage stage) {
        container = new StackPane();
        initContainer();
        scene = new Scene(container);

        stage.setTitle(TITLE);
        stage.getIcons().add(new Image("file:image/icon.png"));
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}