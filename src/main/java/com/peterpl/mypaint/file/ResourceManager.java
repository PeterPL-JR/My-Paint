package com.peterpl.mypaint.file;

import com.peterpl.mypaint.image.*;
import javafx.embed.swing.*;
import javafx.scene.image.*;

import java.awt.image.*;
import java.util.function.*;

public class ResourceManager {
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
