package com.peterpl.mypaint;

import com.peterpl.mypaint.file.*;
import com.peterpl.mypaint.lang.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class MyPaint extends Application {
    public static final String TITLE = "MyPaint";

    private Scene scene;
    private StackPane container;

    private void initContainer() {
        LanguageManager.init();
    }

    @Override
    public void start(Stage stage) {
        container = new StackPane();
        initContainer();
        scene = new Scene(container);

        stage.setTitle(TITLE);
        stage.getIcons().add(ResourceManager.loadImage("icon"));
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}