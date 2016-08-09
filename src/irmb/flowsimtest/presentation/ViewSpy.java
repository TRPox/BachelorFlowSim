package irmb.flowsimtest.presentation;

import irmb.flowsim.presentation.GraphicViewPresenter;
import irmb.flowsim.presentation.View;

/**
 * Created by Sven on 09.08.2016.
 */
public class ViewSpy implements View {


    private boolean paintObjectWasCalled;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private int timesPaintObjectCalled;

    public ViewSpy(GraphicViewPresenter presenter) {

    }

    public void paintObject(double startX, double startY, double endX, double endY) {
        paintObjectWasCalled = true;
        timesPaintObjectCalled++;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public boolean wasPaintObjectCalled() {
        return paintObjectWasCalled;
    }

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }

    public int getTimesPaintObjectCalled() {
        return timesPaintObjectCalled;
    }
}
