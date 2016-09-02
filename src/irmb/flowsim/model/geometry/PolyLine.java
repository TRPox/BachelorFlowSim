package irmb.flowsim.model.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sven on 02.09.2016.
 */
public class PolyLine implements Shape {

    private List<Point> pointList = new ArrayList<>();

    public void addPoint(Point point) {
        pointList.add(point);
    }

    public void removePoint(Point point) {
        pointList.remove(point);
    }

    public List<Point> getAllPoints() {
        return pointList;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}
