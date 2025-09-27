package com.peterpl.mypaint.utils;

public interface IDObject {
    String getID();

    static String build(String ... elements) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < elements.length; i++) {
            str.append(elements[i]);
            if(i < elements.length - 1) {
                str.append('.');
            }
        }
        return str.toString();
    }

    static String getResourcePath(IDObject obj) {
        return obj.getID().replace('.', '/');
    }
}
