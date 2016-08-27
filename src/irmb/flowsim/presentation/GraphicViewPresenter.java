package irmb.flowsim.presentation;

import irmb.flowsim.model.geometry.Point;

/**
 * Created by Sven on 27.07.2016.
 */
public class GraphicViewPresenter {

    private View view;
    private int timesCalled;
    private Point lastPoint;
    private String objectType;
    private boolean paintEnabled;

    public GraphicViewPresenter() {

    }

    public void handleLeftClick(int x, int y) {
        timesCalled++;
        if (paintEnabled) {
            paintShapes(x, y);
            lastPoint = new Point(x, y);
        }
    }

    private void paintShapes(int x, int y) {
        if (objectType.equals("Line")) {
            if (timesCalled == 2)
                view.paintLine(lastPoint, new Point(x, y));
        } else if (objectType.equals("Rectangle")) {
            if (timesCalled == 2)
                view.paintRectangle(lastPoint, new Point(x, y));
        } else {
            if (timesCalled >= 2)
                view.paintLine(lastPoint, new Point(x, y));
        }
    }

    public void handleRightClick(int x, int y) {
        paintEnabled = false;
    }

    public void activatePaintMode(String type) {
        objectType = type;
        timesCalled = 0;
        paintEnabled = true;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void deactivatePaintMode() {

    }


}
