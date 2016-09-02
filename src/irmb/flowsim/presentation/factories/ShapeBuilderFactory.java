package irmb.flowsim.presentation.factories;

import irmb.flowsim.presentation.builders.ShapeBuilder;

/**
 * Created by Sven on 31.08.2016.
 */
public interface ShapeBuilderFactory {
    ShapeBuilder makeShapeBuilder(String type);
}
