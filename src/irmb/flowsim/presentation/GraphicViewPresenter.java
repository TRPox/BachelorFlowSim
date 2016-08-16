package irmb.flowsim.presentation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sven on 27.07.2016.
 */
public class GraphicViewPresenter {

    private View view;
    private int timesCalled;
    private boolean paintMode;
    private String objectType;
    private List<Integer> coordinates = new ArrayList<>();
    private String[] twoClickObjectTypes = {"Line", "Rectangle", "Circle"};
    private boolean mouseMoved;

    public GraphicViewPresenter() {

    }

    public void handleLeftClick(int x, int y) {
        if (paintMode) {
            if (mouseMoved)
                adjustLastPoint(x, y);
            else
                addPoint(x, y);
            timesCalled++;
            mouseMoved = false;
            if (isTwoClickObjectType())
                callPaintObjectAfterSecondClick();

        }
    }

    private void addPoint(int x, int y) {
        coordinates.add(x);
        coordinates.add(y);
    }

    private boolean isTwoClickObjectType() {
        for (String twoClickObjectType : twoClickObjectTypes)
            if (twoClickObjectType.equals(objectType))
                return true;
        return false;
    }

    private void callPaintObjectAfterSecondClick() {
        if (timesCalled == 2)
            view.paintObject(objectType, coordinates);
    }

    public void handleRightClick(int x, int y) {
        if (paintMode)
            if (timesCalled > 1)
                if (objectType.equals("PolyLine"))
                    view.paintObject(objectType, coordinates);
        deactivatePaintMode();
    }

    public void handleMouseMove(int x, int y) {
        if (paintMode)
            if (!mouseMoved) {
                addPoint(x, y);
                mouseMoved = true;
            } else {
                adjustLastPoint(x, y);
            }
        if (!isObjectFinished())
            view.paintObject(objectType, coordinates);
    }

    private boolean isObjectFinished() {
        return !(timesCalled > 0 && timesCalled < 2);
    }

    private void adjustLastPoint(int x, int y) {
        coordinates.set(coordinates.size() - 2, x);
        coordinates.set(coordinates.size() - 1, y);
    }


    public void activatePaintMode(String type) {
        paintMode = true;
        objectType = type;
        timesCalled = 0;
        coordinates.clear();
    }

    public void deactivatePaintMode() {
        paintMode = false;
    }

    public void setView(View view) {
        this.view = view;
    }

}
