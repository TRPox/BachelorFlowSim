package irmb.flowsim.model.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sven on 27.08.2016.
 */
public class PolyLine implements Shape {

    private List<Point> pointList = new ArrayList<>();

    public PolyLine() {
    }

    public PolyLine(List<Point> pointList) {
        this.pointList = pointList;
    }

    public void addPoint(Point point) {
        pointList.add(point);
    }

    public Point getPoint(int index) {
        return pointList.get(index);
    }

    public void removePoint(Point point) {
        pointList.remove(point);
    }

    public List<Point> getPointList() {
        return pointList;
    }
}
