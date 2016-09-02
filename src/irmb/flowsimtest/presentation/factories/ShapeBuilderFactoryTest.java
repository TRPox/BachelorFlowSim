package irmb.flowsimtest.presentation.factories;

import irmb.flowsim.presentation.builders.LineBuilder;
import irmb.flowsim.presentation.builders.PolyLineBuilder;
import irmb.flowsim.presentation.builders.RectangleBuilder;
import irmb.flowsim.presentation.builders.ShapeBuilder;
import irmb.flowsim.presentation.factories.ShapeBuilderFactory;
import irmb.flowsim.presentation.factories.ShapeBuilderFactoryImpl;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Created by Sven on 02.09.2016.
 */
public class ShapeBuilderFactoryTest {

    private ShapeBuilderFactory shapeBuilderFactory;
    private ShapeBuilder shapeBuilder;

    @Before
    public void setUp() throws Exception {
        shapeBuilderFactory = new ShapeBuilderFactoryImpl(null);
    }

    @Test
    public void testMakeLineBuilder() {
        shapeBuilder = shapeBuilderFactory.makeShapeBuilder("Line");
        assertThat(shapeBuilder, is(instanceOf(LineBuilder.class)));
    }

    @Test
    public void testMakeRectangleBuilder() {
        shapeBuilder = shapeBuilderFactory.makeShapeBuilder("Rectangle");
        assertThat(shapeBuilder, is(instanceOf(RectangleBuilder.class)));
    }

    @Test
    public void testMakePolyLineBuilder() {
        shapeBuilder = shapeBuilderFactory.makeShapeBuilder("PolyLine");
        assertThat(shapeBuilder, is(instanceOf(PolyLineBuilder.class)));
    }
}
