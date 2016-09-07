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
    private Point second;

    @Before
    public void setUp() throws Exception {
        factory = new ShapeBuilderFactoryImpl(new ShapeFactoryStub());
        builder = (CircleBuilder) factory.makeShapeBuilder("Circle");
        second = new Point(10, 5);
    }

    public class OnePointAddedContext {

        private Point first;

        @Before
        public void setUp() {
            first = new Point(5, 3);
            builder.addPoint(first);
        }

        private double getExpectedRadius() {
            return first.distanceTo(second);
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
        }
    }


}
