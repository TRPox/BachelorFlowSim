package irmb.flowsimtest.model.geometry;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsimtest.model.geometry.ShapeVisitorSpy.VISITED_SHAPE;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sven on 07.09.2016.
 */
public class LineTest {

    @Test
    public void whenReceivingVisitor_shouldCallVisitLine() {
        ShapeVisitorSpy visitor = new ShapeVisitorSpy();
        Line line = new Line();
        line.accept(visitor);
        assertEquals(VISITED_SHAPE.LINE, visitor.getVisitedShapeType());
    }


}
