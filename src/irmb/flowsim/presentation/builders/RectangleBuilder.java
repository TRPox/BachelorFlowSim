package irmb.flowsim.presentation.builders;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.presentation.factories.ShapeFactory;

/**
 * Created by Sven on 02.09.2016.
 */
public class RectangleBuilder extends ShapeBuilder {

    private Rectangle rectangle;
    private int pointsAdded;

    public RectangleBuilder(ShapeFactory factory) {
        super(factory);
        rectangle = (Rectangle) factory.makeShape("Rectangle");
    }

    @Override
    public void addPoint(Point point) {
        if (pointsAdded == 0)
            rectangle.setFirst(point);
        else if (pointsAdded == 1) {
            rectangle.setSecond(point);
            objectFinished = true;
        }
        pointsAdded++;
    }

    @Override
    public void setLastPoint(Point point) {
        if (pointsAdded == 0)
            addPoint(point);
        else if (pointsAdded == 1)
            rectangle.setFirst(point);
        else
            rectangle.setSecond(point);
    }

    @Override
    public Shape getShape() {
        return rectangle;
    }
}
