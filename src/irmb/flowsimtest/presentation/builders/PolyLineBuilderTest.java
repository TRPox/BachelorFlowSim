package irmb.flowsimtest.presentation.builders;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.PolyLine;
import irmb.flowsim.presentation.builders.PolyLineBuilder;
import irmb.flowsim.presentation.builders.ShapeBuilder;
import irmb.flowsim.presentation.factories.ShapeFactory;
import irmb.flowsimtest.presentation.factories.ShapeFactoryStub;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sven on 02.09.2016.
 */
public class PolyLineBuilderTest extends PolyLine {

    private ShapeBuilder builder;
    private final Point first = new Point(5, 3);
    private final Point second = new Point(8, 4);

    @Before
    public void setUp() throws Exception {
        ShapeFactory factory = new ShapeFactoryStub();
        builder = new PolyLineBuilder(factory);
    }

    @Test
    public void whenAddingOnePoint_shouldHaveCorrectPoint() {
        builder.addPoint(first);

        PolyLineBuilderTest polyLine = (PolyLineBuilderTest) builder.getShape();
        assertEquals(first, polyLine.getPointList().get(0));
    }

    @Test
    public void whenSettingLastPointBeforeAddingPoint_shouldAddPoint() {
        builder.setLastPoint(first);

        PolyLineBuilderTest polyLine = (PolyLineBuilderTest) builder.getShape();
        assertEquals(first, polyLine.getPointList().get(0));
    }

    @Test
    public void whenSettingLastPointAfterAddingPoint_shouldAdjustExistingPoint() {
        builder.addPoint(first);
        builder.setLastPoint(second);

        PolyLineBuilderTest polyLine = (PolyLineBuilderTest) builder.getShape();
        assertEquals(second, polyLine.getPointList().get(0));
    }

    @Test
    public void whenSettingLastPointAfterAddingTwoPoints_shouldAdjustSecondPoint() {
        Point third = new Point(9, 7);

        builder.addPoint(first);
        builder.addPoint(second);
        builder.setLastPoint(third);

        PolyLineBuilderTest polyLine = (PolyLineBuilderTest) builder.getShape();
        assertEquals(third, polyLine.getPointList().get(1));
    }
}
