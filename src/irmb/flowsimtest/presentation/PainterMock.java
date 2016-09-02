package irmb.flowsimtest.presentation;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.Painter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sven on 02.09.2016.
 */
public class PainterMock extends Painter {
    private Point start;
    private Point end;
    private boolean wasPaintLineCalled;
    private boolean wasPaintRectangleCalled;
    private int timesPaintLineCalled;
    private List<Point> allCoordinates = new ArrayList<>();


    @Override
    protected void paintLine(Point start, Point end) {
        this.start = start;
        this.end = end;
        wasPaintLineCalled = true;
        timesPaintLineCalled++;
        allCoordinates.add(start);
        allCoordinates.add(end);
    }

    @Override
    protected void paintRectangle(Point start, Point end) {
        this.start = start;
        this.end = end;
        wasPaintRectangleCalled = true;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public boolean getWasPaintLineCalled() {
        return wasPaintLineCalled;
    }

    public boolean getWasPaintRectangleCalled() {
        return wasPaintRectangleCalled;
    }

    public int getTimesPaintLineCalled() {
        return timesPaintLineCalled;
    }

    public List<Point> getAllCoordinates() {
        return allCoordinates;
    }
}
