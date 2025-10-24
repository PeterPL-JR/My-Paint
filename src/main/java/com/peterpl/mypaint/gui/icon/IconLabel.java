package com.peterpl.mypaint.gui.icon;

import com.peterpl.mypaint.gui.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class IconLabel extends MBoxPanel {
    private final Label label;

    public IconLabel(String iconPath, String text) {
        MIcon icon = new MIcon(iconPath);
        label = new Label();

        getStyleClass().add("icon-label");
        setText(text);

        add(icon, label);
    }

    public IconLabel(String iconPath) {
        this(iconPath, "");
    }

    public void setText(String text) {
        label.setText(text);
    }
}
