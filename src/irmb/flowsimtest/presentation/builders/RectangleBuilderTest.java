package irmb.flowsimtest.presentation.builders;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsim.presentation.builders.RectangleBuilder;
import irmb.flowsim.presentation.factories.ShapeFactory;
import irmb.flowsimtest.presentation.factories.ShapeFactoryStub;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Sven on 02.09.2016.
 */
public class RectangleBuilderTest extends Rectangle {

    private RectangleBuilder rectangleBuilder;
    private Point first;
    private Point second;

    @Before
    public void setUp() throws Exception {
        ShapeFactory factory = new ShapeFactoryStub();
        rectangleBuilder = new RectangleBuilder(factory);
        first = new Point(5, 3);
        second = new Point(7, 8);
    }

    @Test
    public void whenAddingOnePoint_firstShouldEqualPoint() {
        rectangleBuilder.addPoint(first);

        RectangleBuilderTest rectangle = (RectangleBuilderTest) rectangleBuilder.getShape();
        assertEquals(first, rectangle.getFirst());
    }

    @Test
    public void whenAddingTwoPoints_rectangleShouldHaveCorrectCoordinates() {
        rectangleBuilder.addPoint(first);
        rectangleBuilder.addPoint(second);

        RectangleBuilderTest rectangle = (RectangleBuilderTest) rectangleBuilder.getShape();
        assertEquals(first, rectangle.getFirst());
        assertEquals(second, rectangle.getSecond());
    }

    @Test
    public void whenAddingThirdPoint_rectangleShouldBeUnchanged() {
        Point unused = new Point(0, 0);

        rectangleBuilder.addPoint(first);
        rectangleBuilder.addPoint(second);
        rectangleBuilder.addPoint(unused);

        RectangleBuilderTest rectangle = (RectangleBuilderTest) rectangleBuilder.getShape();
        assertEquals(first, rectangle.getFirst());
        assertEquals(second, rectangle.getSecond());
    }

    @Test
    public void whenAddingOnePoint_isObjectFinishedShouldBeFalse() {
        rectangleBuilder.addPoint(first);
        assertFalse(rectangleBuilder.isObjectFinished());
    }

    @Test
    public void whenAddingTwoPoints_isObjectFinishedShouldBeTrue() {
        rectangleBuilder.addPoint(first);
        rectangleBuilder.addPoint(second);
        assertTrue(rectangleBuilder.isObjectFinished());
    }
}
