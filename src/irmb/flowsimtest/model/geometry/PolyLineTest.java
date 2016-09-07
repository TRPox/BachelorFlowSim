package irmb.flowsimtest.model.geometry;

import irmb.flowsim.model.geometry.PolyLine;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsimtest.model.geometry.ShapeVisitorSpy.VISITED_SHAPE;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sven on 07.09.2016.
 */
public class PolyLineTest {
    @Test
    public void whenReceivingVisitor_shouldCallVisitPolyLine() {
        ShapeVisitorSpy visitor = new ShapeVisitorSpy();
        PolyLine polyLine = new PolyLine();
        polyLine.accept(visitor);
        assertEquals(VISITED_SHAPE.POLYLINE, visitor.getVisitedShapeType());
    }
}
