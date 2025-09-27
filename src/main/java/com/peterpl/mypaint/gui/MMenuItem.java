package com.peterpl.mypaint.gui;

import com.peterpl.mypaint.file.*;
import com.peterpl.mypaint.lang.*;
import javafx.scene.control.*;
import javafx.scene.image.*;

public class MMenuItem extends MenuItem {
    private final String name, parentName;

    private static final int ICON_SIZE = 24;

    public MMenuItem(String name, String parentName) {
        super(LanguageManager.translate("menu", parentName, name));
        this.name = name;
        this.parentName = parentName;
        setIcon();
    }

    private void setIcon() {
        Image image = ResourceManager.loadImage("menu/" + parentName + "/" + name, ICON_SIZE, ICON_SIZE);
        ImageView icon = new ImageView(image);
        setGraphic(icon);
    }
}
