package com.peterpl.mypaint.gui.footer.zoom;

import com.peterpl.mypaint.gui.*;
import com.peterpl.mypaint.gui.canvas.*;

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
}
