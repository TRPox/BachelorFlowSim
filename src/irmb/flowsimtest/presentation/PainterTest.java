package irmb.flowsimtest.presentation;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Sven on 02.09.2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class PainterTest {

    private final double delta = 0.000001;
    private PainterSpy painter;
    private Point first;
    private Point end;
    private Point second;

    @Before
    public void setUp() {
        painter = new PainterSpy();
        first = new Point(5, 9);
        second = new Point(8, 10);
        end = new Point(3, 4);
    }

    @Test
    public void whenReceivingLine_shouldCallPaintLine() {
        Line line = new Line();
        painter.visit(line);
        assertTrue(painter.wasPaintLineCalled());
    }

    @Test
    public void whenReceivingLine_shouldCallPaintLineWithCorrectCoordinates() {
        Line line = new Line();
        line.setStart(first);
        line.setEnd(end);

        painter.visit(line);
        assertEquals(first, painter.getFirstReceived());
        assertEquals(end, painter.getSecondReceived());
    }

    @Test
    public void whenReceivingRectangle_shouldCallPaintRectangle() {
        Rectangle rectangle = new Rectangle();

        painter.visit(rectangle);
        assertTrue(painter.wasPaintRectangleCalled());
    }

    @Test
    public void whenReceivingRectangle_shouldCallPaintRectangleWithCorrectCoordinates() {
        Rectangle rectangle = new Rectangle();
        rectangle.setFirst(first);
        rectangle.setSecond(end);

        painter.visit(rectangle);
        assertEquals(first, painter.getFirstReceived());
        assertEquals(end, painter.getSecondReceived());
    }

    @Test
    public void whenReceivingPolyLineWithLessThanTwoPoints_shouldNotCallPaintLine() {
        PolyLine polyLine = new PolyLine();

        painter.visit(polyLine);
        assertFalse(painter.wasPaintLineCalled());

        polyLine.addPoint(new Point(0, 0));
        painter.visit(polyLine);
        assertFalse(painter.wasPaintLineCalled());
    }

    @Test
    public void whenReceivingPolyLineWithTwoPoints_shouldCallPaintLine() {
        PolyLine polyLine = new PolyLine();
        polyLine.addPoint(new Point(0, 0));
        polyLine.addPoint(new Point(0, 0));

        painter.visit(polyLine);
        assertTrue(painter.wasPaintLineCalled());
    }

    @Test
    public void whenReceivingPolyLineWithTwoPoints_shouldCallPaintLineWithCorrectCoordinates() {
        PolyLine polyLine = new PolyLine();
        polyLine.addPoint(first);
        polyLine.addPoint(end);

        painter.visit(polyLine);
        assertEquals(first, painter.getFirstReceived());
        assertEquals(end, painter.getSecondReceived());
    }

    @Test
    public void whenReceivingPolyLineWithThreePoints_shouldCallPaintLineTwice() {
        PolyLine polyLine = makePolyLineWithThreePoints();

        painter.visit(polyLine);
        assertEquals(2, painter.getTimesPaintLineCalled());
    }

    @Test
    public void whenReceivingPolyLineWithThreePoints_shouldCallPaintLineWithCorrectCoordinates() {
        PolyLine polyLine = makePolyLineWithThreePoints();

        painter.visit(polyLine);
        assertEquals(first, painter.getReceivedPointList().get(0));
        assertEquals(second, painter.getReceivedPointList().get(1));
        assertEquals(second, painter.getReceivedPointList().get(2));
        assertEquals(end, painter.getReceivedPointList().get(3));
    }

    @Test
    public void whenReceivingCircle_shouldCallPaintCircle() {
        Circle circle = new Circle();

        painter.visit(circle);
        assertTrue(painter.wasPaintCircleCalled());
    }

    @Test
    public void whenReceivingCircle_shouldCallPaintCircleWithCorrectCenterAndRadius() {
        Circle circle = new Circle();
        circle.setCenter(first);
        circle.setRadius(first.distanceTo(second));

        painter.visit(circle);
        assertEquals(first, painter.getFirstReceived());
        assertEquals(first.distanceTo(second), painter.getReceivedRadius(), delta);
    }

    private PolyLine makePolyLineWithThreePoints() {
        PolyLine polyLine = new PolyLine();
        polyLine.addPoint(first);
        polyLine.addPoint(second);
        polyLine.addPoint(end);
        return polyLine;
    }


}
