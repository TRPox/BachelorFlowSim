package irmb.flowsimtest.presentation;

import irmb.flowsim.model.geometry.*;
import irmb.flowsim.presentation.Painter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sven on 09.08.2016.
 */
public class PainterSpy extends Painter {


    private int timesPaintLineCalled;
    private boolean paintLineWasCalled;
    private boolean paintRectangleWasCalled;
    private boolean paintCircleWasCalled;

    private double receivedRadius;
    private Point firstReceived;
    private Point secondReceived;
    private List<Point> pointList = new ArrayList<>();

    public PainterSpy() {

    }


    protected void paintLine(Point start, Point end) {
        paintLineWasCalled = true;
        timesPaintLineCalled++;
        firstReceived = start;
        secondReceived = end;
        pointList.add(firstReceived);
        pointList.add(secondReceived);
    }


    protected void paintRectangle(Point start, Point end) {
        paintRectangleWasCalled = true;
        firstReceived = start;
        secondReceived = end;
    }

    @Override
    protected void paintCircle(Point center, double radius) {
        paintCircleWasCalled = true;
        this.receivedRadius = radius;
        firstReceived = center;
    }


    public boolean wasPaintLineCalled() {
        return paintLineWasCalled;
    }

    public Point getFirstReceived() {
        return firstReceived;
    }

    public Point getSecondReceived() {
        return secondReceived;
    }

    public int getTimesPaintLineCalled() {
        return timesPaintLineCalled;
    }

    public List<Point> getReceivedPointList() {
        return pointList;
    }

    public boolean wasPaintRectangleCalled() {
        return paintRectangleWasCalled;
    }

    public boolean wasPaintCircleCalled() {
        return paintCircleWasCalled;
    }

    public double getReceivedRadius() {
        return receivedRadius;
    }
}
