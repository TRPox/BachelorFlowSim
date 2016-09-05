package irmb.flowsimtest.presentation;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.presentation.factories.ShapeFactoryImpl;
import irmb.flowsim.presentation.GraphicViewPresenter;
import irmb.flowsim.presentation.factories.ShapeBuilderFactoryImpl;
import irmb.flowsim.presentation.factories.ShapeFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Sven on 27.07.2016.
 */

@RunWith(HierarchicalContextRunner.class)
public class GraphicViewPresenterTestWithViewSpy {

    private GraphicViewPresenter sut;
    private PainterSpy painterSpy;

    private final int firstStartX = 564;
    private final int firstStartY = 321;
    private final int firstEndX = 741;
    private final int firstEndY = 1123;
    private final int secondStartX = 631;
    private final int secondStartY = 336;

    @Before
    public void setUp() {
        ShapeFactory shapeFactory = new ShapeFactoryImpl();
        ShapeBuilderFactoryImpl shapeBuilderFactory = new ShapeBuilderFactoryImpl(shapeFactory);
        sut = new GraphicViewPresenter(shapeBuilderFactory);
        painterSpy = new PainterSpy();
        sut.setPainter(painterSpy);
    }

    private void assertActualPointEqualsExpected(double actualX, double actualY, double expectedX, double expectedY) {
        double delta = 0.00001;
        assertEquals(actualX, expectedX, delta);
        assertEquals(actualY, expectedY, delta);
    }

    private void transmitTwoPointsToPresenter(int startX, int startY, int endX, int endY) {
        sut.handleLeftClick(startX, startY);
        sut.handleLeftClick(endX, endY);
    }


    public class ActivatedPaintModeContext {


        public class BuildLineContext {

            @Before
            public void setUp() {
                sut.activatePaintMode("Line");
            }

            @Test
            public void buildLineAcceptanceTest() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);

                assertTrue(painterSpy.wasPaintLineCalled());
                assertActualPointEqualsExpected(firstStartX, firstStartY, painterSpy.getLastStartX(), painterSpy.getLastStartY());
                assertActualPointEqualsExpected(firstEndX, firstEndY, painterSpy.getLastEndX(), painterSpy.getLastEndY());
            }

            @Test
            public void whenLeftClickingOnce_shouldNotCallPaintObject() {
                sut.handleLeftClick(firstStartX, firstStartY);

                assertFalse(painterSpy.wasPaintLineCalled());
            }

            @Test
            public void whenLeftClickingTwice_shouldCallPaintObject() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);

                assertTrue(painterSpy.wasPaintLineCalled());
            }

            @Test
            public void whenLeftClickingTwice_paintLineShouldReceiveCorrectCoordinates() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);

                assertActualPointEqualsExpected(firstStartX, firstStartY, painterSpy.getLastStartX(), painterSpy.getLastStartY());
                assertActualPointEqualsExpected(firstEndX, firstEndY, painterSpy.getLastEndX(), painterSpy.getLastEndY());
            }

            @Test
            public void whenLeftClickingThreeTimes_shouldCallPaintLineOnce() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
                sut.handleLeftClick(secondStartX, secondStartY);

                assertEquals(1, painterSpy.getTimesPaintLineCalled());
            }
        }

        public class BuildPolyLineContext {

            @Before
            public void setUp() {
                sut.activatePaintMode("PolyLine");
            }

            @Test
            public void buildPolyLineAcceptanceTest() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
                sut.handleLeftClick(secondStartX, secondStartY);

                assertEquals(2, painterSpy.getTimesPaintLineCalled());
                assertActualPointEqualsExpected(firstStartX, firstStartY, painterSpy.getAllCoordinates().get(0), painterSpy.getAllCoordinates().get(1));
                assertActualPointEqualsExpected(firstEndX, firstEndY, painterSpy.getAllCoordinates().get(2), painterSpy.getAllCoordinates().get(3));
                assertActualPointEqualsExpected(firstEndX, firstEndY, painterSpy.getAllCoordinates().get(4), painterSpy.getAllCoordinates().get(5));
                assertActualPointEqualsExpected(secondStartX, secondStartY, painterSpy.getAllCoordinates().get(6), painterSpy.getAllCoordinates().get(7));
            }


            @Test
            public void whenLeftClickingThreeTimes_shouldCallPaintLineTwice() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
                sut.handleLeftClick(secondStartX, secondStartY);

                assertEquals(2, painterSpy.getTimesPaintLineCalled());
            }

            @Test
            public void whenLeftClickingFourTimes_shouldCallPaintLineThreeTimes() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
                sut.handleLeftClick(secondStartX, secondStartY);
                sut.handleLeftClick(0, 0);

                assertEquals(3, painterSpy.getTimesPaintLineCalled());
            }

        }

        public class BuildRectangleContext {
            @Before
            public void setUp() {
                sut.activatePaintMode("Rectangle");
            }

            @Test
            public void buildRectangleAcceptanceTest() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);

                assertTrue(painterSpy.wasPaintRectangleCalled());
                assertActualPointEqualsExpected(firstStartX, firstStartY, painterSpy.getLastStartX(), painterSpy.getLastStartY());
                assertActualPointEqualsExpected(firstEndX, firstEndY, painterSpy.getLastEndX(), painterSpy.getLastEndY());
            }

            @Test
            public void whenLeftClickingTwice_shouldCallPaintRectangle() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);

                assertTrue(painterSpy.wasPaintRectangleCalled());
            }

            @Test
            public void whenLeftClickingTwice_paintRectangleShouldReceiveCorrectCoordinates() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);

                assertActualPointEqualsExpected(firstStartX, firstStartY, painterSpy.getLastStartX(), painterSpy.getLastStartY());
                assertActualPointEqualsExpected(firstEndX, firstEndY, painterSpy.getLastEndX(), painterSpy.getLastEndY());
            }
        }

        public class BuildCircleContext {

            private final double delta = 0.000001;

            @Test
            public void buildCircleAcceptanceTest() {
                sut.activatePaintMode("Circle");
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);

                assertTrue(painterSpy.wasPaintCircleCalled());
                assertActualPointEqualsExpected(firstStartX, firstStartY, painterSpy.getLastStartX(), painterSpy.getLastStartY());
                int deltaX = Math.abs(firstStartX - firstEndX);
                int deltaY = Math.abs(firstStartY - firstEndY);
                double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                assertEquals(distance, painterSpy.getReceivedRadius(), delta);
            }
        }

        public class ResetPaintingContext {
            @Test
            public void resetPaintingAcceptanceTest() {
                sut.activatePaintMode("Line");
                sut.handleLeftClick(firstStartX, firstStartY);
                sut.activatePaintMode("Line");
                sut.handleLeftClick(firstEndX, firstEndY);

                assertFalse(painterSpy.wasPaintLineCalled());
            }
        }


        public class CancelPaintingContext {
            @Test
            public void cancelPaintLineAcceptanceTest() {
                sut.activatePaintMode("Line");
                leftClickThenRightClick();
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);

                assertFalse(painterSpy.wasPaintLineCalled());
            }

            @Test
            public void cancelPaintPolyLineAcceptanceTest() {
                sut.activatePaintMode("PolyLine");
                leftClickThenRightClick();
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);

                assertFalse(painterSpy.wasPaintLineCalled());
            }

            private void leftClickThenRightClick() {
                sut.handleLeftClick(firstStartX, firstStartY);
                sut.handleRightClick(0, 0);
            }
        }


    }


    public class DeactivatedPaintModeContext {

        @Before
        public void setUp() {
            sut.deactivatePaintMode();
        }

        @Test
        public void nothing() {

        }
    }


}