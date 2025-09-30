package com.peterpl.mypaint.gui.menu;

import javafx.scene.control.*;

import java.util.*;

public class MainMenu extends MenuBar {
    private final HashMap<String, MMenuGroup> menus = new HashMap<>();

    public MMenuGroup addGroup(String name) {
        MMenuGroup menuGroup = new MMenuGroup(name);
        getMenus().add(menuGroup);
        menus.put(name, menuGroup);
        return menuGroup;
    }
}
