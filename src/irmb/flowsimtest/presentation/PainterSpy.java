package irmb.flowsimtest.presentation;

import irmb.flowsim.model.geometry.*;
import irmb.flowsim.presentation.Painter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sven on 09.08.2016.
 */
public class PainterSpy extends Painter {


    private boolean paintLineWasCalled;
    private boolean paintRectangleWasCalled;
    private int timesPaintLineCalled;
    private int lastStartX;
    private int lastStartY;
    private int lastEndX;
    private int lastEndY;
    private List<Integer> allCoordinates = new ArrayList<>();
    private int timesPaintRectangleCalled;
    private boolean paintCircleWasCalled;
    private double receivedRadius;


    public PainterSpy() {

    }


    protected void paintLine(Point start, Point end) {
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


    protected void paintRectangle(Point start, Point end) {
        paintRectangleWasCalled = true;
        timesPaintRectangleCalled++;
        lastStartX = start.getX();
        lastStartY = start.getY();
        lastEndX = end.getX();
        lastEndY = end.getY();
    }

    @Override
    protected void paintCircle(Point center, double radius) {
        paintCircleWasCalled = true;
        lastStartX = center.getX();
        lastStartY = center.getY();
        this.receivedRadius = radius;
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

    public boolean wasPaintCircleCalled() {
        return paintCircleWasCalled;
    }

    public double getReceivedRadius() {
        return receivedRadius;
    }
}
