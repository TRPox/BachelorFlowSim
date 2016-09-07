package irmb.flowsimtest.model.geometry;

import irmb.flowsim.model.geometry.Circle;
import irmb.flowsimtest.model.geometry.ShapeVisitorSpy.VISITED_SHAPE;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sven on 07.09.2016.
 */
public class CircleTest {
    @Test
    public void whenReceivingVisitor_shouldCallVisitCircle() {
        ShapeVisitorSpy visitor = new ShapeVisitorSpy();
        Circle circle = new Circle();
        circle.accept(visitor);
        assertEquals(VISITED_SHAPE.CIRCLE, visitor.getVisitedShapeType());
    }
}
