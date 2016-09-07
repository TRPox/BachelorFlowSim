package irmb.flowsim.presentation.builders;

import irmb.flowsim.model.geometry.Circle;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.presentation.factories.ShapeFactory;

/**
 * Created by Sven on 05.09.2016.
 */
public class CircleBuilder extends ShapeBuilder {

    private Circle circle;
    private int pointsAdded;

    public CircleBuilder(ShapeFactory factory) {
        super(factory);
        circle = (Circle) factory.makeShape("Circle");
    }

    @Override
    public void addPoint(Point point) {
        if (pointsAdded == 0)
            circle.setCenter(point);
        else if (pointsAdded == 1) {
            double radius = circle.getCenter().distanceTo(point);
            circle.setRadius(radius);
        }
        pointsAdded++;
    }

    @Override
    public Shape getShape() {
        return circle;
    }
}
