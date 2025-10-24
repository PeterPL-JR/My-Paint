package com.peterpl.mypaint.gui.canvas;

import javafx.application.*;
import javafx.beans.value.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;

import java.util.concurrent.atomic.*;

public class MScrollPanel extends ScrollPane {
    private final PaintCanvas canvas;
    private final CanvasContainer container;

    private double lastX = -1;
    private double lastY = -1;
    private boolean dragging = false;

    private int canvasMouseX, canvasMouseY;

    public MScrollPanel(PaintCanvas canvas) {
        this.canvas = canvas;
        container = new CanvasContainer(canvas, this);
        setContent(container);

        widthProperty().addListener((obs, oldv, newv) -> resize());
        heightProperty().addListener((obs, oldv, newv) -> resize());

        setStyle("-fx-border-color: " + CanvasContainer.BG_COLOUR + ";");

        initMouse();
        centerViewOnInit();
    }

    public void resize() {
        container.resize();
    }

    public void centerView() {
        setHvalue(0.5);
        setVvalue(0.5);
    }

    private void centerViewOnInit() {
        AtomicReference<ChangeListener<? super Bounds>> scrollListener = new AtomicReference<>();
        AtomicInteger counter = new AtomicInteger();

        ChangeListener<? super Bounds> listener = (obs, oldv, newv) -> {
            centerView();
            counter.incrementAndGet();
            if(counter.get() >= 4) {
                initSizeListener();
                viewportBoundsProperty().removeListener(scrollListener.get());
            }
        };
        scrollListener.set(listener);
        viewportBoundsProperty().addListener(listener);
    }

    public void setZoom(int zoom) {
        canvas.setZoom(zoom);
        resize();
    }

    public int getCanvasMouseX() {
        return canvasMouseX;
    }

    public int getCanvasMouseY() {
        return canvasMouseY;
    }

    private void initSizeListener() {
        widthProperty().addListener((obs, oldVal, newVal) -> {
            double v = getVvalue();
            Platform.runLater(() -> setVvalue(v));
        });
        heightProperty().addListener((obs, oldVal, newVal) -> {
            double h = getHvalue();
            Platform.runLater(() -> setHvalue(h));
        });
    }

    private void initMouse() {
        container.setOnMousePressed(this::onMousePressed);
        container.setOnMouseReleased(this::onMouseReleased);

        container.setOnMouseDragged(this::onMouseDragged);

        setOnMouseMoved(this::updateMousePosition);

        addEventFilter(MouseEvent.MOUSE_DRAGGED, e -> {
            if(!dragging) {
                updateMousePosition(e);
            }
        });
    }

    private void onMousePressed(MouseEvent e) {
        if(e.getButton() == MouseButton.MIDDLE) {
            dragging = true;
            lastX = e.getSceneX();
            lastY = e.getSceneY();
            setCursor(Cursor.CLOSED_HAND);
        }
    }

    private void onMouseReleased(MouseEvent e) {
        if(e.getButton() == MouseButton.MIDDLE) {
            dragging = false;
            setCursor(Cursor.DEFAULT);
        }
    }

    private void onMouseDragged(MouseEvent e) {
        if (dragging) {
            double dx = e.getSceneX() - lastX;
            double dy = e.getSceneY() - lastY;

            double contentWidth = getContent().getBoundsInLocal().getWidth();
            double contentHeight = getContent().getBoundsInLocal().getHeight();

            double viewportWidth = getViewportBounds().getWidth();
            double viewportHeight = getViewportBounds().getHeight();

            double hd = dx / (contentWidth - viewportWidth);
            double vd = dy / (contentHeight - viewportHeight);

            setHvalue(getHvalue() - hd);
            setVvalue(getVvalue() - vd);

            lastX = e.getSceneX();
            lastY = e.getSceneY();
        }
    }

    private void updateMousePosition(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        Bounds canvasPosition = canvas.localToScene(canvas.getBoundsInLocal());
        Bounds scrollPosition = localToScene(getBoundsInLocal());

        double offsetX = canvasPosition.getMinX() - scrollPosition.getMinX();
        double offsetY = canvasPosition.getMinY() - scrollPosition.getMinY();

        canvasMouseX = (int) ((x - offsetX) / (canvas.getZoom() / 100.0));
        canvasMouseY = (int) ((y - offsetY) / (canvas.getZoom() / 100.0));

        displayMousePosition();
    }

    private void displayMousePosition() {
        canvas.myPaint.cursorPositionLabel.setText(canvasMouseX, canvasMouseY);
    }
}
