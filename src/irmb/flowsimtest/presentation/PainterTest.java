package irmb.flowsimtest.presentation;

import irmb.flowsim.model.geometry.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Sven on 02.09.2016.
 */
public class PainterTest {

    private final double delta = 0.000001;
    private PainterMock painter;
    private Point first;
    private Point end;
    private Point second;

    @Before
    public void setUp() {
        painter = new PainterMock();
        first = new Point(5, 9);
        second = new Point(8, 10);
        end = new Point(3, 4);
    }

    @Test
    public void whenReceivingLine_shouldCallPaintLine() {
        Line line = new Line();
        painter.paintObject(line);
        assertTrue(painter.getWasPaintLineCalled());
    }

    @Test
    public void whenReceivingLine_shouldCallPaintLineWithCorrectCoordinates() {
        Line line = new Line();
        line.setStart(first);
        line.setEnd(end);

        painter.paintObject(line);
        assertEquals(first, painter.getStart());
        assertEquals(end, painter.getEnd());
    }

    @Test
    public void whenReceivingRectangle_shouldCallPaintRectangle() {
        Rectangle rectangle = new Rectangle();

        painter.paintObject(rectangle);
        assertTrue(painter.getWasPaintRectangleCalled());
    }

    @Test
    public void whenReceivingRectangle_shouldCallPaintRectangleWithCorrectCoordinates() {
        Rectangle rectangle = new Rectangle();
        rectangle.setFirst(first);
        rectangle.setSecond(end);

        painter.paintObject(rectangle);
        assertEquals(first, painter.getStart());
        assertEquals(end, painter.getEnd());
    }

    @Test
    public void whenReceivingPolyLineWithLessThanTwoPoints_shouldNotCallPaintLine() {
        PolyLine polyLine = new PolyLine();

        painter.paintObject(polyLine);
        assertFalse(painter.getWasPaintLineCalled());

        polyLine.addPoint(new Point(0, 0));
        painter.paintObject(polyLine);
        assertFalse(painter.getWasPaintLineCalled());
    }

    @Test
    public void whenReceivingPolyLineWithTwoPoints_shouldCallPaintLine() {
        PolyLine polyLine = new PolyLine();
        polyLine.addPoint(new Point(0, 0));
        polyLine.addPoint(new Point(0, 0));

        painter.paintObject(polyLine);
        assertTrue(painter.getWasPaintLineCalled());
    }

    @Test
    public void whenReceivingPolyLineWithTwoPoints_shouldCallPaintLineWithCorrectCoordinates() {
        PolyLine polyLine = new PolyLine();
        polyLine.addPoint(first);
        polyLine.addPoint(end);

        painter.paintObject(polyLine);
        assertEquals(first, painter.getStart());
        assertEquals(end, painter.getEnd());
    }

    @Test
    public void whenReceivingPolyLineWithThreePoints_shouldCallPaintLineWithLastTwoPoints() {
        PolyLine polyLine = new PolyLine();
        polyLine.addPoint(first);
        polyLine.addPoint(second);
        polyLine.addPoint(end);

        painter.paintObject(polyLine);
        assertEquals(second, painter.getPaintedPoints().get(0));
        assertEquals(end, painter.getPaintedPoints().get(1));
    }

    @Test
    public void whenReceivingCircle_shouldCallPaintCircle() {
        Circle circle = new Circle();

        painter.paintObject(circle);
        assertTrue(painter.getWasPaintCircleCalled());
    }

    @Test
    public void whenReceivingCircle_shouldCallPaintCircleWithCorrectCenterAndRadius() {
        Circle circle = new Circle();
        circle.setCenter(first);
        circle.setRadius(first.distanceTo(second));

        painter.paintObject(circle);
        assertEquals(first, painter.getStart());
        assertEquals(first.distanceTo(second), painter.getReceivedRadius(), delta);
    }

}
