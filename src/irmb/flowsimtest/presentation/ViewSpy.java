package irmb.flowsimtest.presentation;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.GraphicViewPresenter;
import irmb.flowsim.presentation.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by Sven on 09.08.2016.
 */
public class ViewSpy implements View {


    private boolean paintLineWasCalled;
    private boolean paintRectangleWasCalled;
    private int timesPaintLineCalled;
    private int lastStartX;
    private int lastStartY;
    private int lastEndX;
    private int lastEndY;
    private List<Integer> allCoordinates = new ArrayList<>();
    private int timesPaintRectangleCalled;


    public ViewSpy(GraphicViewPresenter presenter) {

    }

    @Override
    public void paintLine(Point start, Point end) {
        paintLineWasCalled = true;
        timesPaintLineCalled++;
        lastStartX = start.getX();
        lastStartY = start.getY();
        lastEndX = end.getX();
        lastEndY = end.getY();
        allCoordinates.add(lastStartX);
        allCoordinates.add(lastStartY);
        allCoordinates.add(lastEndX);
        allCoordinates.add(lastEndY);
    }

    @Override
    public void paintRectangle(Point start, Point end) {
        paintRectangleWasCalled = true;
        timesPaintRectangleCalled++;
        lastStartX = start.getX();
        lastStartY = start.getY();
        lastEndX = end.getX();
        lastEndY = end.getY();
    }

    public boolean wasPaintLineCalled() {
        return paintLineWasCalled;
    }

    public int getLastStartX() {
        return lastStartX;
    }

    public int getLastStartY() {
        return lastStartY;
    }

    public int getLastEndX() {
        return lastEndX;
    }

    public int getLastEndY() {
        return lastEndY;
    }

    public int getTimesPaintLineCalled() {
        return timesPaintLineCalled;
    }

    public List<Integer> getAllCoordinates() {
        return allCoordinates;
    }

    public boolean wasPaintRectangleCalled() {
        return paintRectangleWasCalled;
    }

    public int getTimesPaintRectangleCalled() {
        return timesPaintRectangleCalled;
    }
}
