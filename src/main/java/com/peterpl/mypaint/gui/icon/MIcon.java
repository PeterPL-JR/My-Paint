package com.peterpl.mypaint.gui.icon;

import com.peterpl.mypaint.file.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class MIcon extends StackPane {
    private static final int SIZE = 24;

    public MIcon(String iconPath) {
        ImageView imageView = new ImageView(ResourceManager.loadImage(iconPath, SIZE, SIZE));
        getChildren().add(imageView);

        setMaxWidth(SIZE);
        setMaxHeight(SIZE);

        setPickOnBounds(true);
    }
}
