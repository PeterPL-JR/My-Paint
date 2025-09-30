package com.peterpl.mypaint.gui.canvas;

import javafx.scene.control.*;

public class MScrollPanel extends ScrollPane {
    private final PaintCanvas canvas;
    private final CanvasContainer container;

    public MScrollPanel(PaintCanvas canvas) {
        this.canvas = canvas;
        container = new CanvasContainer(canvas, this);
        setContent(container);

        widthProperty().addListener((obs, oldv, newv) -> resize());
        heightProperty().addListener((obs, oldv, newv) -> resize());
    }

    public void resize() {
        container.resize();
    }
}
