package irmb.flowsimtest.presentation.builders;


import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.Circle;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.builders.CircleBuilder;
import irmb.flowsim.presentation.factories.ShapeBuilderFactory;
import irmb.flowsim.presentation.factories.ShapeBuilderFactoryImpl;
import irmb.flowsimtest.presentation.factories.ShapeFactoryStub;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

/**
 * Created by Sven on 05.09.2016.
 */

@RunWith(HierarchicalContextRunner.class)
public class CircleBuilderTest extends Circle {

    private final double delta = 0.000001;
    private CircleBuilder builder;
    private Point first = new Point(5, 3);

    @Before
    public void setUp() throws Exception {
        ShapeBuilderFactory factory = new ShapeBuilderFactoryImpl(new ShapeFactoryStub());
        builder = (CircleBuilder) factory.makeShapeBuilder("Circle");
    }

    @Test
    public void whenAddingOnePoint_centerShouldEqualAddedPoint() {
        builder.addPoint(first);

        CircleBuilderTest circle = (CircleBuilderTest) builder.getShape();
        assertEquals(first, circle.getCenter());
    }

    @Test
    public void whenAddingOnePoint_isObjectFinishedShouldBeFalse() {
        builder.addPoint(first);

        assertFalse(builder.isObjectFinished());
    }

    @Test
    public void whenSettingLastPoint_shouldDoNothing() {
        builder.setLastPoint(first);
        CircleBuilderTest circle = (CircleBuilderTest) builder.getShape();
        assertNull(circle.getCenter());
    }

    public class OnePointAddedContext {
        private Point second = new Point(10, 5);

        @Before
        public void setUp() {
            builder.addPoint(first);
        }

        private double getExpectedRadius() {
            return first.distanceTo(second);
        }

        @Test
        public void whenAddingSecondPoint_shouldSetRadius() {
            builder.addPoint(second);

            CircleBuilderTest circle = (CircleBuilderTest) builder.getShape();
            double expectedRadius = getExpectedRadius();
            assertEquals(first, circle.getCenter());
            assertEquals(expectedRadius, circle.getRadius(), delta);
        }

        @Test
        public void whenAddingSecondPoint_isObjectFinishedShouldBeTrue() {
            builder.addPoint(second);

            assertTrue(builder.isObjectFinished());
        }

        @Test
        public void whenSettingLastPoint_shouldAdjustCenter() {
            builder.setLastPoint(second);

            CircleBuilderTest circle = (CircleBuilderTest) builder.getShape();
            assertEquals(second, circle.getCenter());
        }

        public class TwoPointsAddedContext {

            private final Point third = new Point(2, 8);

            @Before
            public void setUp() {
                builder.addPoint(second);
            }

            @Test
            public void whenAddingThirdPoint_circleShouldBeUnchanged() {
                Point unused = new Point(0, 0);
                builder.addPoint(unused);

                CircleBuilderTest circle = (CircleBuilderTest) builder.getShape();
                assertEquals(getExpectedRadius(), circle.getRadius(), delta);
            }

            @Test
            public void whenSettingLastPoint_shouldAdjustRadius() {
                builder.setLastPoint(third);

                CircleBuilderTest circle = (CircleBuilderTest) builder.getShape();
                assertEquals(first.distanceTo(third), circle.getRadius(), delta);
            }

            @Test
            public void whenSettingLastPointAfterAddingPoint_shouldAdjustRadius() {
                Point point = new Point(10, 11);

                builder.addPoint(third);
                builder.setLastPoint(point);

                CircleBuilderTest circle = (CircleBuilderTest) builder.getShape();
                assertEquals(first, circle.getCenter());
                assertEquals(point.distanceTo(first), circle.getRadius(), delta);
            }
        }
    }


}
