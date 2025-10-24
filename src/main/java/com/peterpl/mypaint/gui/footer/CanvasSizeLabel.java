package com.peterpl.mypaint.gui.footer;

import com.peterpl.mypaint.gui.icon.*;

public class CanvasSizeLabel extends IconLabel {
    public CanvasSizeLabel() {
        super("footer/canvas_size");
        getStyleClass().add("footer-icon-label");
        setText(0, 0);
    }

    public void setText(int width, int height) {
        setText(width + " x " + height);
    }
}
