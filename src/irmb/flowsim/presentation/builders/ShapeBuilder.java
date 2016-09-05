package irmb.flowsim.presentation.builders;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.presentation.factories.ShapeFactory;

/**
 * Created by Sven on 02.09.2016.
 */
public abstract class ShapeBuilder {

    private final ShapeFactory factory;
    protected boolean objectFinished;

    public ShapeBuilder(ShapeFactory factory) {
        this.factory = factory;
    }

    public abstract void addPoint(Point point);

    public abstract Shape getShape();

    public boolean isObjectFinished() {
        return objectFinished;
    }
}

