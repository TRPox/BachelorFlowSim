package irmb.flowsim.presentation.builders;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.PolyLine;
import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.presentation.factories.ShapeFactory;

/**
 * Created by Sven on 02.09.2016.
 */
public class PolyLineBuilder extends ShapeBuilder {

    private PolyLine polyLine;

    public PolyLineBuilder(ShapeFactory factory) {
        super(factory);
        polyLine = (PolyLine) factory.makeShape("PolyLine");
    }

    @Override
    public void addPoint(Point point) {
        polyLine.addPoint(point);
    }

    @Override
    public Shape getShape() {
        return polyLine;
    }
}
