package irmb.flowsimtest.presentation.builders;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.builders.LineBuilder;
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
public class LineBuilderTest extends Line {

    private LineBuilder builder;
    private Point start;
    private Point end;

    @Before
    public void setUp() throws Exception {
        ShapeFactory factory = new ShapeFactoryStub();
        builder = new LineBuilder(factory);
        start = new Point(5, 3);
        end = new Point(7, 8);
    }

    @Test
    public void whenAddingOnePoint_lineStartShouldEqualPoint() {
        builder.addPoint(start);

        LineBuilderTest line = (LineBuilderTest) builder.getShape();
        assertEquals(line.getStart(), start);
    }

    @Test
    public void whenAddingTwoPoints_lineStartShouldEqualFirstLineEndShouldEqualSecond() {
        builder.addPoint(start);
        builder.addPoint(end);

        LineBuilderTest line = (LineBuilderTest) builder.getShape();
        assertEquals(line.getStart(), start);
        assertEquals(line.getEnd(), end);
    }

    @Test
    public void whenAddingThirdPoint_lineShouldBeUnchanged() {
        Point unused = new Point(0, 0);

        builder.addPoint(start);
        builder.addPoint(end);
        builder.addPoint(unused);

        LineBuilderTest line = (LineBuilderTest) builder.getShape();
        assertEquals(line.getStart(), start);
        assertEquals(line.getEnd(), end);
    }

    @Test
    public void whenAddingOnePoint_isObjectFinishedShouldBeFalse() {
        builder.addPoint(start);
        assertFalse(builder.isObjectFinished());
    }

    @Test
    public void whenAddingTwoPoints_isObjectFinishedShouldBeTrue() {
        builder.addPoint(start);
        builder.addPoint(end);
        assertTrue(builder.isObjectFinished());
    }
}
