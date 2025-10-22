package com.peterpl.mypaint.gui;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.layout.*;

public class MBoxPanel extends HBox {
    public MBoxPanel() {
        setAlignment(Pos.CENTER);
    }

    public void add(Node node, Node ... nodes) {
        getChildren().add(node);
        for(Node n : nodes) {
            getChildren().add(n);
        }
    }
}
