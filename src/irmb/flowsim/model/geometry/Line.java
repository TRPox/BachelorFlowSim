package irmb.flowsim.model.geometry;

/**
 * Created by Sven on 28.07.2016.
 */
public class Line implements Shape {

    private Point start;
    private Point end;

    public Line() {
    }

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}
