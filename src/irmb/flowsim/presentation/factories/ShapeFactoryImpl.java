package irmb.flowsim.presentation.factories;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.presentation.factories.ShapeFactory;

/**
 * Created by Sven on 31.08.2016.
 */
public class ShapeFactoryImpl implements ShapeFactory {
    @Override
    public Shape makeShape(String type) {
        switch (type) {
            case "Line":
                return new Line();
            case "Rectangle":
                return new Rectangle();
            case "PolyLine":
                return new Line();
            default:
                return null;
        }
    }
}
