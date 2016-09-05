package irmb.flowsimtest.presentation.factories;

import irmb.flowsim.model.geometry.*;
import irmb.flowsim.presentation.factories.ShapeFactory;
import irmb.flowsim.presentation.factories.ShapeFactoryImpl;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Sven on 02.09.2016.
 */
public class ShapeFactoryImplTest {

    private ShapeFactory shapeFactory;

    @Before
    public void setUp() throws Exception {
        shapeFactory = new ShapeFactoryImpl();
    }

    @Test
    public void testMakeLine() {
        assertThat(shapeFactory.makeShape("Line"), is(instanceOf(Line.class)));
    }

    @Test
    public void testMakeRectangle() {
        assertThat(shapeFactory.makeShape("Rectangle"), is(instanceOf(Rectangle.class)));
    }

    @Test
    public void testMakePolyLine() {
        assertThat(shapeFactory.makeShape("PolyLine"), is(instanceOf(PolyLine.class)));
    }

    @Test
    public void testMakeCircle() {
        assertThat(shapeFactory.makeShape("Circle"), is(instanceOf(Circle.class)));
    }

    @Test
    public void testInvalidType() {
        assertNull(shapeFactory.makeShape("Invalid"));
    }

}
