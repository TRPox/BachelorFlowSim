package irmb.flowsimtest.presentation;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.PolyLine;
import irmb.flowsim.model.geometry.Rectangle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Sven on 02.09.2016.
 */
public class PainterTest {

    private PainterMock painter;
    private Point start;
    private Point end;
    private Point second;

    @Before
    public void setUp() {
        painter = new PainterMock();
        start = new Point(5, 9);
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
        line.setStart(start);
        line.setEnd(end);

        painter.paintObject(line);
        assertEquals(start, painter.getStart());
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
        rectangle.setFirst(start);
        rectangle.setSecond(end);

        painter.paintObject(rectangle);
        assertEquals(start, painter.getStart());
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
    public void whenReceivingPolyLineWithTwoPoints_shouldCallPaintLineOnce() {
        PolyLine polyLine = new PolyLine();
        polyLine.addPoint(new Point(0, 0));
        polyLine.addPoint(new Point(0, 0));

        painter.paintObject(polyLine);
        assertTrue(painter.getWasPaintLineCalled());
    }

    @Test
    public void whenReceivingPolyLineWithTwoPoints_shouldCallPaintLineWithCorrectCoordinates() {
        PolyLine polyLine = new PolyLine();
        polyLine.addPoint(start);
        polyLine.addPoint(end);

        painter.paintObject(polyLine);
        assertEquals(start, painter.getStart());
        assertEquals(end, painter.getEnd());
    }

    @Test
    public void whenReceivingPolyLineWithThreePoints_shouldCallPaintLineTwice() {
        Point second = new Point(0, 0);
        PolyLine polyLine = new PolyLine();
        polyLine.addPoint(start);
        polyLine.addPoint(second);
        polyLine.addPoint(end);

        painter.paintObject(polyLine);
        assertEquals(2, painter.getTimesPaintLineCalled());
    }

    @Test
    public void whenReceivingPolyLineWithThreePoints_shouldCallPaintLineWithCorrectCoordinates() {
        PolyLine polyLine = new PolyLine();
        polyLine.addPoint(start);
        polyLine.addPoint(second);
        polyLine.addPoint(end);

        painter.paintObject(polyLine);
        assertEquals(start, painter.getAllCoordinates().get(0));
        assertEquals(second, painter.getAllCoordinates().get(1));
        assertEquals(second, painter.getAllCoordinates().get(2));
        assertEquals(end, painter.getAllCoordinates().get(3));

    }

}
