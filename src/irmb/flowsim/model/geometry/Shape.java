package irmb.flowsim.model.geometry;

/**
 * Created by Sven on 02.09.2016.
 */
public interface Shape {
    void accept(ShapeVisitor visitor);
}
