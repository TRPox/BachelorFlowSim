package irmb.flowsim.model.geometry;

/**
 * Created by Sven on 05.09.2016.
 */
public class Circle implements Shape {
    private Point center;
    private double radius;

    @Override
    public void accept(ShapeVisitor visitor) {
         visitor.visit(this);
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
