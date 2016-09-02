package irmb.flowsim.model.geometry;

import irmb.flowsim.presentation.factories.ShapeFactory;

/**
 * Created by Sven on 02.09.2016.
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
                return new PolyLine();
        }
        return null;
    }
}
