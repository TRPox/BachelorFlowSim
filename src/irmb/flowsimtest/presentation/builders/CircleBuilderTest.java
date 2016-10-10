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

import static org.junit.Assert.assertEquals;

/**
 * Created by Sven on 05.09.2016.
 */

@RunWith(HierarchicalContextRunner.class)
public class CircleBuilderTest extends Circle {

    private final double delta = 0.000001;

    private ShapeBuilderFactory factory;
    private CircleBuilder builder;
    private Point first;
    private Point second;

    @Before
    public void setUp() throws Exception {
        factory = new ShapeBuilderFactoryImpl(new ShapeFactoryStub());
        builder = (CircleBuilder) factory.makeShapeBuilder("Circle");
        first = new Point(5, 3);
        second = new Point(10, 5);
    }

    private double getExpectedRadius() {
        return first.distanceTo(second);
    }

    @Test
    public void whenSettingLastPointBeforeAddingPoint_shouldSetCenter() {
        builder.setLastPoint(first);

        CircleBuilderTest circle = (CircleBuilderTest) builder.getShape();
        assertEquals(first, circle.getCenter());
    }

    @Test
    public void whenAddingPointAfterSettingLastPoint_shouldSetRadius() {
        builder.setLastPoint(first);
        builder.addPoint(second);

        CircleBuilderTest circle = (CircleBuilderTest) builder.getShape();
        assertEquals(getExpectedRadius(), circle.getRadius(), delta);
    }

    public class OnePointAddedContext {


        @Before
        public void setUp() {
            builder.addPoint(first);
        }


        @Test
        public void centerShouldEqualAddedPoint() {
            CircleBuilderTest circle = (CircleBuilderTest) builder.getShape();
            assertEquals(first, circle.getCenter());
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
        public void whenSettingLastPointAfterAddingOnePoint_shouldAdjustCenter() {
            builder.setLastPoint(second);

            CircleBuilderTest circle = (CircleBuilderTest) builder.getShape();
            assertEquals(second, circle.getCenter());
        }

        public class TwoPointsAddedContext {
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
            public void whenSettingLastPointAfterAddingTwoPoints_shouldOnlyAdjustRadius() {
                Point third = new Point(9, 7);
                builder.setLastPoint(third);

                CircleBuilderTest circle = (CircleBuilderTest) builder.getShape();
                assertEquals(first, circle.getCenter());
                assertEquals(first.distanceTo(third), circle.getRadius(), delta);
            }
        }
    }


}
