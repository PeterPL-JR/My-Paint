package com.peterpl.mypaint.gui.footer.zoom;

import com.peterpl.mypaint.gui.*;

public class ZoomMenu extends MBoxPanel {
    private final ZoomSlider slider;
    private final IconButton plus, minus;
    private final PercentLabel percent;

    public ZoomMenu() {
        slider = new ZoomSlider();
        plus = new IconButton("menu/view/zoom_in");
        minus = new IconButton("menu/view/zoom_out");
        percent = new PercentLabel(this, slider);

        plus.setListener(this::zoomIn);
        minus.setListener(this::zoomOut);

        slider.setListener(percent::setPercent);

        add(percent, minus, slider, plus);
    }

    public void zoomIn() {
        slider.plusStep();
        percent.closeInput();
    }

    public void zoomOut() {
        slider.minusStep();
        percent.closeInput();
    }
}
