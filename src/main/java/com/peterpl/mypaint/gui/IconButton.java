package com.peterpl.mypaint.gui;

import com.peterpl.mypaint.file.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class IconButton extends StackPane {
    private static final int SIZE = 24;

    private Runnable listener = () -> {};

    public IconButton(String iconPath) {
        ImageView imageView = new ImageView(ResourceManager.loadImage(iconPath, SIZE, SIZE));
        getChildren().add(imageView);

        setMaxWidth(SIZE);
        setMaxHeight(SIZE);

        setCursor(Cursor.HAND);
        setPickOnBounds(true);
        setOnMouseClicked(e -> listener.run());
        getStyleClass().add("icon-button");
    }

    public void setListener(Runnable listener) {
        this.listener = listener;
    }
}
