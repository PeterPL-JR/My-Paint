package com.peterpl.mypaint.gui.footer.zoom;

import javafx.scene.control.*;

import java.util.function.*;

public class ZoomSlider extends Slider {
    private int percent = 100;
    private Consumer<Integer> listener = p -> {};

    public ZoomSlider() {
        setMin(0);
        setMax(2);
        setShowTickMarks(true);
        setMinorTickCount(0);
        setMajorTickUnit(1);
        setBlockIncrement(10);
        setPrefWidth(200);
        setValue(1);
        getStyleClass().add("slider");

        valueProperty().addListener((obs, oldv, newv) -> {
            percent = (int) Math.round(Math.pow(100, newv.doubleValue()));
            listener.accept(percent);
        });
    }

    public void setListener(Consumer<Integer> listener) {
        this.listener = listener;
    }

    public int getPercent() {
        return percent;
    }
}
