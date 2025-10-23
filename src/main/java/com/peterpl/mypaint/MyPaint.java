package com.peterpl.mypaint;

import com.peterpl.mypaint.file.*;
import com.peterpl.mypaint.gui.canvas.*;
import com.peterpl.mypaint.gui.footer.*;
import com.peterpl.mypaint.gui.footer.zoom.*;
import com.peterpl.mypaint.gui.menu.*;
import com.peterpl.mypaint.input.*;
import com.peterpl.mypaint.lang.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class MyPaint extends Application {
    public static final String TITLE = "MyPaint";

    private Scene scene;
    private BorderPane container;

    private PaintCanvas canvas;
    private MScrollPanel scrollPanel;
    private ZoomMenu zoomMenu;

    private void initContainer() {
        LanguageManager.init();
        KeyboardManager.init();

        initCanvas();
        initFooter();
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
                .addItem("zoom_in", zoomMenu::zoomIn)
                .addItem("zoom_out", zoomMenu::zoomOut);

        container.setTop(menu);
    }

    private void initCanvas() {
        canvas = new PaintCanvas();

        scrollPanel = new MScrollPanel(canvas);
        container.setCenter(scrollPanel);
    }

    private void initFooter() {
        FooterMenu footer = new FooterMenu();
        zoomMenu = new ZoomMenu();

        footer.add(zoomMenu);
        container.setBottom(footer);
    }

    @Override
    public void start(Stage stage) {
        container = new BorderPane();
        scene = new Scene(container);
        scene.getStylesheets().add(MyPaint.class.getResource("/style.css").toExternalForm());
        initContainer();

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