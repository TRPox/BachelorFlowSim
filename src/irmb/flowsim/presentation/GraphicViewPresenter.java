package irmb.flowsim.presentation;

import irmb.flowsimtest.presentation.ViewSpy;

/**
 * Created by Sven on 27.07.2016.
 */
public class GraphicViewPresenter {

    private View view;
    private int timesCalled;
    private int lastX;
    private int lastY;
    private boolean paintMode;

    public GraphicViewPresenter() {

    }

    public void handleLeftClick(int x, int y) {
        if(paintMode) {
            timesCalled++;
            if (timesCalled == 2) {
                view.paintObject(lastX, lastY, x, y);
            }
        lastX = x;
        lastY = y;
        }
    }

    public void activatePaintMode() {
        paintMode = true;
        timesCalled = 0;
    }

    public void deactivatePaintMode() {
        paintMode = false;
    }

    public void setView(View view) {
        this.view = view;
    }
}
