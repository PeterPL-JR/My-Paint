package com.peterpl.mypaint.gui.canvas;

import javafx.scene.canvas.*;
import javafx.scene.paint.*;

public class PaintCanvas extends Canvas {
    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 600;

    public PaintCanvas() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public PaintCanvas(int width, int height) {
        setWidth(width);
        setHeight(height);

        GraphicsContext graphics = getGraphicsContext2D();
        graphics.setFill(Color.WHITE);
        graphics.fillRect(0, 0, getWidth(), getHeight());
    }
}
