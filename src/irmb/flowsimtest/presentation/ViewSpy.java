package irmb.flowsimtest.presentation;

import irmb.flowsim.presentation.GraphicViewPresenter;
import irmb.flowsim.presentation.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sven on 09.08.2016.
 */
public class ViewSpy implements View {


    private boolean paintObjectWasCalled;
    private int timesPaintObjectCalled;
    private List<Integer> allCoordinates = new ArrayList<>();
    private String objectString;

    public ViewSpy(GraphicViewPresenter presenter) {

    }

    @Override
    public void paintObject(List<Integer> coordinates) {
        allCoordinates.clear();
        paintObjectWasCalled = true;
        timesPaintObjectCalled++;
        allCoordinates.addAll(coordinates);
    }

    public boolean wasPaintObjectCalled() {
        return paintObjectWasCalled;
    }

    public double getLastStartX() {
        return allCoordinates.get(allCoordinates.size() - 4);
    }

    public double getLastStartY() {
        return allCoordinates.get(allCoordinates.size() - 3);
    }

    public double getLastEndX() {
        return allCoordinates.get(allCoordinates.size() - 2);
    }

    public double getLastEndY() {
        return allCoordinates.get(allCoordinates.size() - 1);
    }

    public int getTimesPaintObjectCalled() {
        return timesPaintObjectCalled;
    }

    public List<Integer> getAllCoordinates() {
        return allCoordinates;
    }


    public String getObjectString() {
        return objectString;
    }
}
