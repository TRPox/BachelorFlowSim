package irmb.flowsimtest.presentation.builders;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.PolyLine;
import irmb.flowsim.presentation.builders.PolyLineBuilder;
import irmb.flowsim.presentation.builders.ShapeBuilder;
import irmb.flowsim.presentation.factories.ShapeFactory;
import irmb.flowsimtest.presentation.factories.ShapeFactoryStub;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sven on 02.09.2016.
 */
@RunWith(HierarchicalContextRunner.class)
public class PolyLineBuilderTest extends PolyLine {

    private ShapeBuilder builder;
    private final Point first = new Point(5, 3);

    @Before
    public void setUp() {
        ShapeFactory factory = new ShapeFactoryStub();
        builder = new PolyLineBuilder(factory);
    }

    @Test
    public void whenAddingOnePoint_shouldHaveCorrectPoint() {
        builder.addPoint(first);

        PolyLineBuilderTest polyLine = (PolyLineBuilderTest) builder.getShape();
        assertEquals(first, polyLine.getPointList().get(0));
    }

    public class OnePointAddedContext {

        private final Point second = new Point(10, 5);

        @Before
        public void setUp() {
            builder.addPoint(first);
        }

        @Test
        public void whenSettingLastPoint_shouldAdjustFirstPoint() {
            builder.setLastPoint(second);

            PolyLineBuilderTest polyLine = (PolyLineBuilderTest) builder.getShape();
            List<Point> pointList = polyLine.getPointList();
            assertEquals(1, pointList.size());
            assertEquals(second, pointList.get(0));
        }

        public class TwoPointsAddedContext {
            @Before
            public void setUp() {
                builder.addPoint(second);
            }

            @Test
            public void whenSettingLastPoint_shouldAdjustSecond() {
                Point third = new Point(10, 11);

                builder.setLastPoint(third);

                PolyLineBuilderTest polyLine = (PolyLineBuilderTest) builder.getShape();
                List<Point> pointList = polyLine.getPointList();
                assertEquals(2, pointList.size());
                assertEquals(first, pointList.get(0));
                assertEquals(third, pointList.get(1));
            }
        }
    }


}
