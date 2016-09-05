package irmb.flowsim.model.geometry;

/**
 * Created by Sven on 02.09.2016.
 */
public interface ShapeVisitor {
    void visit(Line line);

    void visit(Rectangle rectangle);

    void visit(PolyLine polyLine);

    void visit(Circle circle);
}
