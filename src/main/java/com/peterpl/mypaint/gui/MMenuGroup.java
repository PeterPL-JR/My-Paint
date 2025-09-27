package com.peterpl.mypaint.gui;

import com.peterpl.mypaint.lang.*;
import javafx.scene.control.*;

public class MMenuGroup extends Menu {
    private final String name;

    public MMenuGroup(String name) {
        super(LanguageManager.translate("menu", name));
        this.name = name;
    }

    public MMenuGroup addItem(String name) {
        MMenuItem item = new MMenuItem(name, this.name);
        getItems().add(item);
        return this;
    }

    public MMenuGroup addSeparator() {
        getItems().add(new SeparatorMenuItem());
        return this;
    }
}
