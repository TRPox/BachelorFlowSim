package irmb.flowsim.presentation.factories;

import irmb.flowsim.presentation.builders.LineBuilder;
import irmb.flowsim.presentation.builders.PolyLineBuilder;
import irmb.flowsim.presentation.builders.RectangleBuilder;
import irmb.flowsim.presentation.builders.ShapeBuilder;

/**
 * Created by Sven on 31.08.2016.
 */
public class ShapeBuilderFactoryImpl implements ShapeBuilderFactory {

    private final ShapeFactory shapeFactory;

    public ShapeBuilderFactoryImpl(ShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    @Override
    public ShapeBuilder makeShapeBuilder(String type) {
        switch (type) {
            case "Line":
                return new LineBuilder(shapeFactory);
            case "Rectangle":
                return new RectangleBuilder(shapeFactory);
            case "PolyLine":
                return new PolyLineBuilder(shapeFactory);
            default:
                return null;
        }
    }
}
