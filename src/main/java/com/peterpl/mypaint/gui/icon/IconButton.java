package com.peterpl.mypaint.gui.icon;

import javafx.scene.*;

public class IconButton extends MIcon {
    private Runnable listener = () -> {};

    public IconButton(String iconPath) {
        super(iconPath);
        setCursor(Cursor.HAND);
        setOnMouseClicked(e -> listener.run());
        getStyleClass().add("icon-button");
    }

    public void setListener(Runnable listener) {
        this.listener = listener;
    }
}
