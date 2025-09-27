package com.peterpl.mypaint.input;

import com.peterpl.mypaint.file.*;
import javafx.scene.input.*;

import java.util.*;

public class KeyboardManager {
    public static final HashMap<String, KeyCodeCombination> KEYMAP = new HashMap<>();

    public static void init() {
        HashMap<String, String> map = ResourceManager.loadJSON("keymap");
        for(String id : map.keySet()) {
            String keybindString = map.get(id);
            String[] keys = keybindString.split(" \\+ ");

            String mainKeyString = keys[keys.length - 1];
            Optional<KeyCode> mainKeyOptional = Arrays.stream(KeyCode.values()).filter(k -> k.getName().equals(mainKeyString)).findFirst();
            if(mainKeyOptional.isEmpty()) continue;

            KeyCode mainKey = mainKeyOptional.get();
            if(keys.length > 1) {
                addKeybind: {
                    KeyCombination.Modifier[] modifiers = new KeyCombination.Modifier[keys.length - 1];
                    for(int i = 0; i < keys.length - 1; i++) {
                        String modifierString = keys[i];
                        KeyCombination.Modifier modifier = getKeybindModifier(modifierString);
                        if(modifier != null) {
                            modifiers[i] = modifier;
                        } else {
                            break addKeybind;
                        }
                    }
                    KEYMAP.put(id, new KeyCodeCombination(mainKey, modifiers));
                }
            }
        }
    }

    private static KeyCombination.Modifier getKeybindModifier(String name) {
        if(name.equals("Ctrl")) {
            return KeyCombination.CONTROL_DOWN;
        }
        if(name.equals("Shift")) {
            return KeyCombination.SHIFT_DOWN;
        }
        if(name.equals("Alt")) {
            return KeyCombination.ALT_DOWN;
        }
        return null;
    }

    public static KeyCombination getKeybind(String id) {
        return KEYMAP.get(id);
    }
}
