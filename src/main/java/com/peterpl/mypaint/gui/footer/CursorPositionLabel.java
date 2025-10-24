package com.peterpl.mypaint.gui.footer;

import com.peterpl.mypaint.gui.icon.*;

public class CursorPositionLabel extends IconLabel {
    public CursorPositionLabel() {
        super("footer/cursor_position");
        getStyleClass().add("footer-icon-label");
        setText(0, 0);
    }

    public void setText(int cursorX, int cursorY) {
        setText(cursorX + ", " + cursorY);
    }
}
