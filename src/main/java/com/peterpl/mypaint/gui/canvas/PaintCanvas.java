package com.peterpl.mypaint.gui.canvas;

import com.peterpl.mypaint.*;
import javafx.scene.layout.*;

public class PaintCanvas extends Pane {
    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 600;

    public static final int DEFAULT_ZOOM = 100;

    private int width, height, displayWidth, displayHeight;
    private int zoom = DEFAULT_ZOOM;

    final MyPaint myPaint;

    public PaintCanvas(MyPaint myPaint) {
        this.myPaint = myPaint;
        setStyle("-fx-background-color: white;");
        setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
    }

    private void repaint() {
    }

    public int getCanvasWidth() {
        return width;
    }

    public int getCanvasHeight() {
        return height;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        changeDisplaySize();
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
        changeDisplaySize();
    }

    private void changeDisplaySize() {
        displayWidth = width * zoom / 100;
        displayHeight = height * zoom / 100;

        setWidth(displayWidth);
        setHeight(displayHeight);

        setPrefWidth(displayWidth);
        setPrefHeight(displayHeight);

        displayCanvasSize();

        repaint();
    }

    private void displayCanvasSize() {
        myPaint.canvasSizeLabel.setText(width, height);
    }
}
