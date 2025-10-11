package com.peterpl.mypaint.gui.footer.zoom;

import javafx.scene.control.*;

public class PercentLabel extends Label {
    public PercentLabel() {
        setPercent(100);
        getStyleClass().add("percent");
    }

    public void setPercent(int percent) {
        setText(percent + "%");
    }
}
