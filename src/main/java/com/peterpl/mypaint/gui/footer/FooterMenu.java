package com.peterpl.mypaint.gui.footer;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.layout.*;

public class FooterMenu extends BorderPane {
    private final HBox box;

    public FooterMenu() {
        setPrefHeight(30);

        box = new HBox();
        box.setAlignment(Pos.CENTER_RIGHT);
        box.setPadding(new Insets(0, 15, 0, 0));
        setCenter(box);
    }

    public void add(Node node, Node ... nodes) {
        box.getChildren().add(node);
        for(Node n : nodes) {
            box.getChildren().add(n);
        }
    }
}
