package com.peterpl.mypaint.gui.footer.zoom;

import javafx.scene.control.*;

import java.util.function.*;

public class ZoomSlider extends Slider {
    private int percent = 100;
    private int index = getClosestStepIndex(percent);

    private Consumer<Integer> listener = p -> {};
    private boolean valueDirectChange = false;

    private static final int[] SLIDER_STEPS = {
            1, 2, 3, 5, 6, 7, 8,
            10, 13, 17, 20, 25, 33, 50, 67,
            100, 150, 200, 300, 400, 500, 600, 800,
            1000, 1200, 1400, 1600, 2000, 2400, 2800, 3200, 4000, 4800, 5600, 6400, 7600, 8800, 10000
    };

    public static final int MIN_PERCENT = SLIDER_STEPS[0];
    public static final int MAX_PERCENT = SLIDER_STEPS[SLIDER_STEPS.length - 1];

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
            if(valueDirectChange) {
                valueDirectChange = false;
                showPercent();
                return;
            }
            int sliderPercent = valueToPercent(newv.doubleValue());
            int sliderIndex = getClosestStepIndex(sliderPercent);

            if(index != sliderIndex) {
                index = sliderIndex;
                percent = SLIDER_STEPS[index];
            }

            valueProperty().set(percentToValue(percent));
            showPercent();
        });
    }

    public void setListener(Consumer<Integer> listener) {
        this.listener = listener;
    }

    public void plusStep() {
        if(index == SLIDER_STEPS.length - 1 && percent != MAX_PERCENT) {
            percent = MAX_PERCENT;
            showPercent();
            return;
        }

        int newIndex = index + 1;
        if(newIndex < SLIDER_STEPS.length) {
            setPercent(SLIDER_STEPS[newIndex], false);
            index = newIndex;
        }
    }

    public void minusStep() {
        int newIndex = index - 1;
        if(newIndex >= 0) {
            setPercent(SLIDER_STEPS[newIndex], false);
            index = newIndex;
        }
    }

    public int getPercent() {
        return percent;
    }

    void setPercent(int percent, boolean direct) {
        if(percent >= MIN_PERCENT && percent <= MAX_PERCENT) {
            this.percent = percent;
            index = getClosestStepIndex(percent);

            double sliderValue = percentToValue(SLIDER_STEPS[index]);
            if(direct) {
                if(index == SLIDER_STEPS.length - 1 && percent != MAX_PERCENT) {
                    index--;
                    sliderValue = percentToValue(SLIDER_STEPS[index]);
                }
                valueDirectChange = true;
            }
            valueProperty().set(sliderValue);
            showPercent();
        }
    }

    private void showPercent() {
        listener.accept(percent);
    }

    private int valueToPercent(double value) {
        return (int) Math.round(Math.pow(100, value));
    }

    private double percentToValue(int percent) {
        return Math.log10(percent) / 2;
    }

    private int getClosestStep(int percent) {
        return SLIDER_STEPS[getClosestStepIndex(percent)];
    }

    public int getClosestStepIndex(int percent) {
        for(int i = 1; i < SLIDER_STEPS.length; i++) {
            int step = SLIDER_STEPS[i];
            if(percent <= step) {
                int prevStep = SLIDER_STEPS[i - 1];

                int prevStepDiff = Math.abs(percent - prevStep);
                int stepDiff = Math.abs(percent - step);

                return stepDiff < prevStepDiff ? i : i - 1;
            }
        }
        return -1;
    }
}
