package irmb.flowsim.presentation;

import irmb.flowsim.model.geometry.*;

import java.util.List;

/**
 * Created by Sven on 09.08.2016.
 */
public abstract class Painter implements ShapeVisitor {
    public void paintObject(Shape shape) {
        shape.accept(this);
    }

    @Override
    public void visit(Line line) {
        paintLine(line.getStart(), line.getEnd());
    }

    @Override
    public void visit(Rectangle rectangle) {
        paintRectangle(rectangle.getFirst(), rectangle.getSecond());
    }

    @Override
    public void visit(PolyLine polyLine) {
        List<Point> pointList = polyLine.getAllPoints();
        int i = 0;
        while (pointList.size() - 1 > i)
            paintLine(pointList.get(i), pointList.get(++i));

    }

    protected abstract void paintLine(Point start, Point end);

    protected abstract void paintRectangle(Point start, Point end);
}
