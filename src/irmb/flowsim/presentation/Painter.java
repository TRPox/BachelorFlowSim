package irmb.flowsim.presentation;

import irmb.flowsim.model.geometry.*;

import java.util.List;

/**
 * Created by Sven on 09.08.2016.
 */
public abstract class Painter implements ShapeVisitor {
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
        List<Point> pointList = polyLine.getPointList();
        if (polyLine.getPointList().size() < 2)
            return;
        else {
            for (int i = 0; i < pointList.size() - 1; )
                paintLine(pointList.get(i), pointList.get(++i));
        }
    }

    @Override
    public void visit(Circle circle) {
        paintCircle(circle.getCenter(), circle.getRadius());
    }


    protected abstract void paintLine(Point start, Point end);

    protected abstract void paintRectangle(Point start, Point end);

    protected abstract void paintCircle(Point center, double radius);

    public abstract void clear();
}
