package com.peterpl.mypaint.lang;

import com.peterpl.mypaint.file.*;

import java.util.*;

public record Language(String id, String name, HashMap<String, String> dictionary) {
    public String translate(String key) {
        return dictionary.get(key);
    }

    public String translate(String... keys) {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < keys.length; i++) {
            key.append(keys[i]);
            if (i < keys.length - 1) {
                key.append('.');
            }
        }
        return dictionary.get(key.toString());
    }
}
