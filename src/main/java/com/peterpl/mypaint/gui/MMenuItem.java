package com.peterpl.mypaint.gui;

import com.peterpl.mypaint.lang.*;
import javafx.scene.control.*;

public class MMenuItem extends MenuItem {
    public MMenuItem(String name, String parentName) {
        super(LanguageManager.translate("menu", parentName, name));
    }
}
