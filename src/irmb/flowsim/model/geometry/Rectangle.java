package irmb.flowsim.model.geometry;

/**
 * Created by Sven on 27.08.2016.
 */
public class Rectangle implements Shape {
    private Point first;
    private Point second;

    public Rectangle() {
    }

    public Rectangle(Point first, Point second) {
        this.first = first;
        this.second = second;
    }

    public Point getFirst() {
        return first;
    }

    public void setFirst(Point first) {
        this.first = first;
    }

    public Point getSecond() {
        return second;
    }

    public void setSecond(Point second) {
        this.second = second;
    }
}
