package irmb.flowsim.presentation.builders;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.presentation.factories.ShapeFactory;

/**
 * Created by Sven on 02.09.2016.
 */
public class LineBuilder extends ShapeBuilder {

    private Line line;
    private int pointsAdded;

    public LineBuilder(ShapeFactory factory) {
        super(factory);
        line = (Line) factory.makeShape("Line");
    }

    @Override
    public void addPoint(Point point) {
        if (pointsAdded == 0)
            line.setStart(point);
        else if (pointsAdded == 1)
            line.setEnd(point);
        pointsAdded++;
    }

    @Override
    public Shape getShape() {
        return line;
    }
}
