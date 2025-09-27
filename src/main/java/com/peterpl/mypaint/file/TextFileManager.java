package com.peterpl.mypaint.file;

import java.io.*;

public class TextFileManager {
    public static String loadResource(String path) {
        try(BufferedReader reader = new BufferedReader(new FileReader(TextFileManager.class.getResource("/" + path).getFile()))) {
            StringBuilder str = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                str.append(line).append('\n');
            }
            return str.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
