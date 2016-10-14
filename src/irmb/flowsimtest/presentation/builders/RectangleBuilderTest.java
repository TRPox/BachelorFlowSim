package irmb.flowsimtest.presentation.builders;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsim.presentation.builders.RectangleBuilder;
import irmb.flowsim.presentation.factories.ShapeFactory;
import irmb.flowsimtest.presentation.factories.ShapeFactoryStub;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by Sven on 02.09.2016.
 */
@RunWith(HierarchicalContextRunner.class)
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
    public void whenAddingOnePoint_isObjectFinishedShouldBeFalse() {
        rectangleBuilder.addPoint(first);
        assertFalse(rectangleBuilder.isObjectFinished());
    }

    @Test
    public void whenSettingLastPoint_shouldDoNothing() {
        rectangleBuilder.setLastPoint(first);

        RectangleBuilderTest rectangle = (RectangleBuilderTest) rectangleBuilder.getShape();
        assertNull(rectangle.getFirst());
    }

    public class OnePointAddedContext {
        @Before
        public void setUp() {
            rectangleBuilder.addPoint(first);
        }

        @Test
        public void whenAddingSecondPoint_rectangleShouldHaveCorrectCoordinates() {
            rectangleBuilder.addPoint(second);

            RectangleBuilderTest rectangle = (RectangleBuilderTest) rectangleBuilder.getShape();
            assertEquals(first, rectangle.getFirst());
            assertEquals(second, rectangle.getSecond());
        }

        @Test
        public void whenAddingSecondPoint_isObjectFinishedShouldBeTrue() {
            rectangleBuilder.addPoint(second);
            assertTrue(rectangleBuilder.isObjectFinished());
        }

        @Test
        public void whenSettingLastPoint_shouldAdjustFirst() {
            rectangleBuilder.setLastPoint(second);

            RectangleBuilderTest rectangle = (RectangleBuilderTest) rectangleBuilder.getShape();
            assertEquals(second, rectangle.getFirst());
        }

        public class TwoPointsAddedContext {

            private final Point third = new Point(2, 8);

            @Before
            public void setUp() {
                rectangleBuilder.addPoint(second);
            }

            @Test
            public void whenAddingThirdPoint_rectangleShouldBeUnchanged() {
                Point unused = new Point(0, 0);

                rectangleBuilder.addPoint(unused);

                RectangleBuilderTest rectangle = (RectangleBuilderTest) rectangleBuilder.getShape();
                assertEquals(first, rectangle.getFirst());
                assertEquals(second, rectangle.getSecond());
            }

            @Test
            public void whenSettingLastPoint_shouldAdjustSecond() {
                rectangleBuilder.setLastPoint(third);

                RectangleBuilderTest rectangle = (RectangleBuilderTest) rectangleBuilder.getShape();
                assertEquals(third, rectangle.getSecond());
            }

            @Test
            public void whenSettingLastPointAfterAddingPoint_shouldAdjustSecond() {
                Point point = new Point(10, 11);

                rectangleBuilder.addPoint(third);
                rectangleBuilder.setLastPoint(point);

                RectangleBuilderTest rectangle = (RectangleBuilderTest) rectangleBuilder.getShape();
                assertEquals(point, rectangle.getSecond());
            }
        }
    }


}
