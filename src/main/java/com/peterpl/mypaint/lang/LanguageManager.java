package com.peterpl.mypaint.lang;

import com.peterpl.mypaint.file.*;

import java.util.*;

public class LanguageManager {
    public static HashMap<String, Language> LANGUAGES = new HashMap<>();

    public static Language activeLanguage;

    public static void init() {
        HashMap<String, String> languages = ResourceManager.loadJSON("lang/languages");
        for (String id : languages.keySet()) {
            String name = languages.get(id);
            HashMap<String, String> dictionary = ResourceManager.loadJSON("lang/" + id);
            LANGUAGES.put(id, new Language(id, name, dictionary));
        }
        activeLanguage = LANGUAGES.get("en");
    }

    public static String translate(String key) {
        return activeLanguage.translate(key);
    }

    public static String translate(String ... keys) {
        return activeLanguage.translate(keys);
    }
}
