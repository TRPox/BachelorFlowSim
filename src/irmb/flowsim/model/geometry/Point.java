package irmb.flowsim.model.geometry;

/**
 * Created by Sven on 25.08.2016.
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString() {
        return x + ", " + y;
    }

    public double distanceTo(Point point) {
        int deltaX = Math.abs(getX() - point.getX());
        int deltaY = Math.abs(getY() - point.getY());
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
