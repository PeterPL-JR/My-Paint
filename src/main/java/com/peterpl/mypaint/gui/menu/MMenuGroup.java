package com.peterpl.mypaint.gui.menu;

import com.peterpl.mypaint.lang.*;
import com.peterpl.mypaint.utils.*;
import javafx.scene.control.*;

public class MMenuGroup extends Menu implements IDObject {
    private final String name;

    public MMenuGroup(String name) {
        this.name = name;
        setText(LanguageManager.translate(this));
    }

    public MMenuGroup addItem(String name, Runnable action) {
        MMenuItem item = new MMenuItem(name, this, action);
        getItems().add(item);
        return this;
    }

    public MMenuGroup addSeparator() {
        getItems().add(new SeparatorMenuItem());
        return this;
    }

    @Override
    public String getID() {
        return IDObject.build("menu", name);
    }
}
