package com.peterpl.mypaint.file;

import com.peterpl.mypaint.image.*;
import javafx.embed.swing.*;
import javafx.scene.image.*;
import org.json.*;

import java.awt.image.*;
import java.util.*;
import java.util.function.*;

public class ResourceManager {
    public static HashMap<String, String> loadJSON(String path) {
        String text = TextFileManager.loadResource(path + ".json");
        if(text != null) {
            HashMap<String, String> map = new HashMap<>();
            JSONObject obj = new JSONObject(text);

            loadJSON(obj, null, map);

            return map;
        }
        return null;
    }

    private static void loadJSON(JSONObject obj, String previousKey, HashMap<String, String> map) {
        for(String key : obj.keySet()) {
            Object value = obj.get(key);
            if(value instanceof String string) {
                String newKey = key.equals("_") ? previousKey : previousKey + "." + key;
                map.put(newKey, string);
            } else {
                JSONObject newObj = (JSONObject) value;
                String newKey = previousKey != null ? previousKey + "." + key : key;
                loadJSON(newObj, newKey, map);
            }
        }
    }

    public static Image loadImage(String path) {
        return loadImage(() -> ImageManager.loadResource("image/" + path + ".png"));
    }

    public static Image loadImage(String path, int width, int height) {
        return loadImage(() -> ImageManager.scale(ImageManager.loadResource("image/" + path + ".png"), width, height));
    }

    private static Image loadImage(Supplier<BufferedImage> supplier) {
        BufferedImage img = supplier.get();
        if(img != null) {
            return SwingFXUtils.toFXImage(img, null);
        }
        return null;
    }
}
