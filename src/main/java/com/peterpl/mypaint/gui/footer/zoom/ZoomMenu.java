package com.peterpl.mypaint.gui.footer.zoom;

import com.peterpl.mypaint.gui.*;
import com.peterpl.mypaint.gui.canvas.*;
import javafx.scene.input.*;

public class ZoomMenu extends MBoxPanel {
    private final ZoomSlider slider;
    private final IconButton plus, minus;
    private final PercentLabel percentLabel;

    private final MScrollPanel canvasPanel;

    public ZoomMenu(MScrollPanel canvasPanel) {
        this.canvasPanel = canvasPanel;

        slider = new ZoomSlider();
        plus = new IconButton("menu/view/zoom_in");
        minus = new IconButton("menu/view/zoom_out");
        percentLabel = new PercentLabel(this, slider);

        plus.setListener(this::zoomIn);
        minus.setListener(this::zoomOut);

        slider.setListener(this::changeZoom);

        canvasPanel.addEventFilter(ScrollEvent.SCROLL, this::onCanvasScroll);

        add(percentLabel, minus, slider, plus);
    }

    private void changeZoom(int percent) {
        percentLabel.setPercent(percent);
        canvasPanel.setZoom(percent);
    }

    public void zoomIn() {
        slider.plusStep();
        percentLabel.closeInput();
    }

    public void zoomOut() {
        slider.minusStep();
        percentLabel.closeInput();
    }

    private void onCanvasScroll(ScrollEvent e) {
        if(e.isControlDown()) {
            int delta = (int) Math.signum(e.getDeltaY());
            if(delta != 0) {
                zoomByScroll(delta);
            }
            e.consume();
        }
    }

    private void zoomByScroll(int delta) {
        if(delta > 0) {
            zoomIn();
        } else {
            zoomOut();
        }
    }
}
