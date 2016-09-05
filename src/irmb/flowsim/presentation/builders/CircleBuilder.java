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
            double radius = getDistanceToCenter(point);
            circle.setRadius(radius);
        }
        pointsAdded++;
    }

    private double getDistanceToCenter(Point point) {
        Point center = circle.getCenter();
        int deltaX = Math.abs(center.getX() - point.getX());
        int deltaY = Math.abs(center.getY() - point.getY());
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    @Override
    public Shape getShape() {
        return circle;
    }
}
