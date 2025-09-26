package com.peterpl.mypaint.image;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class ImageManager {
    public static BufferedImage load(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage loadResource(String path) {
        try {
            return ImageIO.read(ImageManager.class.getResource("/" + path));
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage scale(BufferedImage img, int width, int height) {
        Image buffer = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        BufferedImage scaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics = scaled.createGraphics();
        graphics.drawImage(buffer, 0, 0, null);
        graphics.dispose();

        return scaled;
    }
}
