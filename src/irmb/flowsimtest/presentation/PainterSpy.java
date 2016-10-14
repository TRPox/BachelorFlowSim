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
    private int timesClearCalled;
    private boolean paintLineWasCalled;
    private boolean paintRectangleWasCalled;
    private boolean paintCircleWasCalled;
    private boolean clearWasCalled;

    private double receivedRadius;
    private Point firstReceived;
    private Point secondReceived;
    private List<Point> pointList = new ArrayList<>();
    private int timesPaintRectangleCalled;
    private int timesPaintCircleCalled;


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
        timesPaintRectangleCalled++;
        firstReceived = start;
        secondReceived = end;
    }

    @Override
    protected void paintCircle(Point center, double radius) {
        paintCircleWasCalled = true;
        timesPaintCircleCalled++;
        this.receivedRadius = radius;
        firstReceived = center;
    }

    @Override
    public void clear() {
        clearWasCalled = true;
        timesClearCalled++;
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

    public boolean wasClearCalled() {
        return clearWasCalled;
    }

    public int getTimesClearCalled() {
        return timesClearCalled;
    }

    public int getTimesPaintRectangleCalled() {
        return timesPaintRectangleCalled;
    }

    public int getTimesPaintCircleCalled() {
        return timesPaintCircleCalled;
    }
}
