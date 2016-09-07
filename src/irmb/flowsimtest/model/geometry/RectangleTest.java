package irmb.flowsimtest.model.geometry;

import irmb.flowsim.model.geometry.Rectangle;
import org.junit.Test;

import static irmb.flowsimtest.model.geometry.ShapeVisitorSpy.VISITED_SHAPE;
import static org.junit.Assert.assertEquals;

/**
 * Created by Sven on 07.09.2016.
 */
public class RectangleTest {

    @Test
    public void whenReceivingVisitor_shouldCallVisitRectangle() {
        ShapeVisitorSpy visitor = new ShapeVisitorSpy();
        Rectangle rectangle = new Rectangle();
        rectangle.accept(visitor);
        assertEquals(VISITED_SHAPE.RECTANGLE, visitor.getVisitedShapeType());
    }


}
