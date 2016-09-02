package irmb.flowsimtest.presentation.factories;

import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.presentation.factories.ShapeFactory;
import irmb.flowsimtest.presentation.builders.LineBuilderTest;
import irmb.flowsimtest.presentation.builders.PolyLineBuilderTest;
import irmb.flowsimtest.presentation.builders.RectangleBuilderTest;

/**
 * Created by Sven on 02.09.2016.
 */
public class ShapeFactoryMock implements ShapeFactory {
    @Override
    public Shape makeShape(String type) {
        switch (type) {
            case "Line":
                return new LineBuilderTest();
            case "Rectangle":
                return new RectangleBuilderTest();
            case "PolyLine":
                return new PolyLineBuilderTest();
            default:
                return null;
        }
    }
}
