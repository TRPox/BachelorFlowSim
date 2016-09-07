package irmb.flowsimtest.model.geometry;

import irmb.flowsim.model.geometry.*;

/**
 * Created by Sven on 07.09.2016.
 */
public class ShapeVisitorSpy implements ShapeVisitor {

    private VISITED_SHAPE visitedShape;

    @Override
    public void visit(Line line) {
        visitedShape = VISITED_SHAPE.LINE;
    }

    @Override
    public void visit(Rectangle rectangle) {
        visitedShape = VISITED_SHAPE.RECTANGLE;
    }

    @Override
    public void visit(PolyLine polyLine) {
        visitedShape = VISITED_SHAPE.POLYLINE;
    }

    @Override
    public void visit(Circle circle) {
        visitedShape = VISITED_SHAPE.CIRCLE;
    }

    public VISITED_SHAPE getVisitedShapeType() {
        return visitedShape;
    }

    public enum VISITED_SHAPE {
        LINE,
        RECTANGLE,
        CIRCLE,
        POLYLINE
    }
}
