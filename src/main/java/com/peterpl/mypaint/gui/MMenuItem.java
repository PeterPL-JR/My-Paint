package com.peterpl.mypaint.gui;

import com.peterpl.mypaint.file.*;
import com.peterpl.mypaint.lang.*;
import com.peterpl.mypaint.utils.*;
import javafx.scene.control.*;
import javafx.scene.image.*;

public class MMenuItem extends MenuItem implements IDObject {
    private final String name;
    private final MMenuGroup group;

    private static final int ICON_SIZE = 24;

    public MMenuItem(String name, MMenuGroup group, Runnable action) {
        this.name = name;
        this.group = group;

        setText(LanguageManager.translate(this));
        setIcon();
        setOnAction(event -> action.run());
    }

    private void setIcon() {
        Image image = ResourceManager.loadImage(IDObject.getResourcePath(this), ICON_SIZE, ICON_SIZE);
        ImageView icon = new ImageView(image);
        setGraphic(icon);
    }

    @Override
    public String getID() {
        return IDObject.build(group.getID(), name);
    }
}
