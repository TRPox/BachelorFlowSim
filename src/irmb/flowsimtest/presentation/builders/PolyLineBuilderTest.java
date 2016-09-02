package irmb.flowsimtest.presentation.builders;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.PolyLine;
import irmb.flowsim.presentation.builders.PolyLineBuilder;
import irmb.flowsim.presentation.builders.ShapeBuilder;
import irmb.flowsim.presentation.factories.ShapeBuilderFactory;
import irmb.flowsim.presentation.factories.ShapeBuilderFactoryImpl;
import irmb.flowsim.presentation.factories.ShapeFactory;
import irmb.flowsimtest.presentation.factories.ShapeFactoryMock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sven on 02.09.2016.
 */
public class PolyLineBuilderTest extends PolyLine {

    @Test
    public void whenAddingOnePoint_shouldHaveCorrectPoint() {
        ShapeFactory factory = new ShapeFactoryMock();
        ShapeBuilder builder = new PolyLineBuilder(factory);
        Point first = new Point(5, 3);

        builder.addPoint(first);

        PolyLineBuilderTest polyLine = (PolyLineBuilderTest) builder.getShape();
        assertEquals(first, polyLine.getAllPoints().get(0));
    }
}
