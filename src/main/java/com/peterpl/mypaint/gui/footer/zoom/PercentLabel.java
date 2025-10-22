package com.peterpl.mypaint.gui.footer.zoom;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;

public class PercentLabel extends Label {
    private final Input input;
    private final HBox parent;

    public PercentLabel(HBox parent, ZoomSlider slider) {
        input = new Input(this, slider);
        this.parent = parent;

        setPercent(100);
        getStyleClass().add("percent");

        setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                openInput();
            }
        });
    }

    public void setPercent(int percent) {
        String text = percent + "%";
        setText(text);
        input.setText(text);
    }

    public void openInput() {
        setInputActive(true);
        parent.getChildren().addFirst(input);
        input.requestFocus();
    }

    public void closeInput() {
        setInputActive(false);
        parent.getChildren().remove(input);
    }

    private void setInputActive(boolean active) {
        input.setManaged(active);
        input.setVisible(active);

        setManaged(!active);
        setVisible(!active);
    }

    private static class Input extends TextField {
        private final PercentLabel label;
        private final ZoomSlider slider;

        public Input(PercentLabel label, ZoomSlider slider) {
            this.label = label;
            this.slider = slider;
            getStyleClass().add("percent-input");
            setPrefWidth(70);
            setManaged(false);
            HBox.setMargin(this, new Insets(0, 5, 0, 5));

            initFocusListener();
            initKeyboardListener();
        }

        private void initFocusListener() {
            focusedProperty().addListener((obs, oldv, newv) -> {
                if(newv) {
                    selectAll();
                } else {
                    label.closeInput();
                }
            });
        }

        private void initKeyboardListener() {
            setOnKeyPressed(event -> {
                if(event.getCode() == KeyCode.ENTER) {
                    commit();
                }
                if(event.getCode() == KeyCode.ESCAPE) {
                    label.closeInput();
                }
            });
        }

        private void commit() {
            String value = getText();
            int percent = Input.getPercentNumber(value);
            if(percent != -1 && percent >= ZoomSlider.MIN_PERCENT && percent <= ZoomSlider.MAX_PERCENT) {
                slider.setPercent(percent, true);
                label.setPercent(percent);
                label.closeInput();
            }
        }

        private static int getPercentNumber(String text) {
            if(text.isEmpty()) return -1;

            if(text.endsWith("%")) {
                text = text.substring(0, text.length() - 1);
            }
            for(int i = 0; i < text.length(); i++) {
                char ch = text.charAt(i);
                if(!(ch >= '0' && ch <= '9')) {
                    return -1;
                }
            }
            return Integer.parseInt(text);
        }
    }
}
