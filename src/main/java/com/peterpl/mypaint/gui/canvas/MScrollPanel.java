package com.peterpl.mypaint.gui.canvas;

import javafx.application.*;
import javafx.beans.value.*;
import javafx.geometry.*;
import javafx.scene.control.*;

import java.util.concurrent.atomic.*;

public class MScrollPanel extends ScrollPane {
    private final PaintCanvas canvas;
    private final CanvasContainer container;

    public MScrollPanel(PaintCanvas canvas) {
        this.canvas = canvas;
        container = new CanvasContainer(canvas, this);
        setContent(container);

        widthProperty().addListener((obs, oldv, newv) -> resize());
        heightProperty().addListener((obs, oldv, newv) -> resize());

        centerViewOnInit();
    }

    public void resize() {
        container.resize();
    }

    public void centerView() {
        setHvalue(0.5);
        setVvalue(0.5);
    }

    private void centerViewOnInit() {
        AtomicReference<ChangeListener<? super Bounds>> scrollListener = new AtomicReference<>();
        AtomicInteger counter = new AtomicInteger();

        ChangeListener<? super Bounds> listener = (obs, oldv, newv) -> {
            centerView();
            counter.incrementAndGet();
            if(counter.get() >= 4) {
                initSizeListener();
                viewportBoundsProperty().removeListener(scrollListener.get());
            }
        };
        scrollListener.set(listener);
        viewportBoundsProperty().addListener(listener);
    }

    private void initSizeListener() {
        widthProperty().addListener((obs, oldVal, newVal) -> {
            double v = getVvalue();
            Platform.runLater(() -> setVvalue(v));
        });
        heightProperty().addListener((obs, oldVal, newVal) -> {
            double h = getHvalue();
            Platform.runLater(() -> setHvalue(h));
        });
    }
}
