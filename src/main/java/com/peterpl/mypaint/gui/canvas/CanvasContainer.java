package com.peterpl.mypaint.gui.canvas;

import javafx.scene.layout.*;

public class CanvasContainer extends StackPane {
    private PaintCanvas canvas;
    private MScrollPanel panel;

    static final String BG_COLOUR = "#cfcfcf";

    public CanvasContainer(PaintCanvas canvas, MScrollPanel panel) {
        this.canvas = canvas;
        this.panel = panel;
        getChildren().add(canvas);
        setStyle("-fx-background-color: " + BG_COLOUR + ";");

        resize();
    }

    public void resize() {
        double windowWidth = panel.getWidth();
        double windowHeight = panel.getHeight();

        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        double width = windowWidth > canvasWidth ? windowWidth * 2 : windowWidth + canvasWidth;
        double height = windowHeight > canvasHeight ? windowHeight * 2 : windowHeight + canvasHeight;

        setPrefWidth(width);
        setPrefHeight(height);
    }
}
