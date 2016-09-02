package irmb.flowsim.presentation.factories;

import irmb.flowsim.model.geometry.Shape;

/**
 * Created by Sven on 02.09.2016.
 */
public interface ShapeFactory {
    Shape makeShape(String type);
}
