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

    public GraphicViewPresenter() {

    }

    public void handleLeftClick(int x, int y) {
        if (paintMode) {
            coordinates.add(x);
            coordinates.add(y);
            timesCalled++;
            for (String twoClickObjectType : twoClickObjectTypes)
                if (twoClickObjectType.equals(objectType))
                    callPaintObjectAfterSecondClick();
        }
    }

    private void callPaintObjectAfterSecondClick() {
        if (timesCalled == 2) {
            view.paintObject(objectType, coordinates);
        }
    }

    public void handleRightClick(int x, int y) {
        if (paintMode)
            if (timesCalled > 1)
                if (objectType.equals("PolyLine"))
                    view.paintObject(objectType, coordinates);
        deactivatePaintMode();
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
