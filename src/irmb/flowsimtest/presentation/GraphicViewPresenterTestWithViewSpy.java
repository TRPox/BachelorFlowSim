package irmb.flowsimtest.presentation;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.presentation.GraphicViewPresenter;
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
    private ViewSpy viewSpy;

    private final int firstStartX = 564;
    private final int firstStartY = 321;
    private final int firstEndX = 741;
    private final int firstEndY = 1123;
    private final int secondStartX = 631;
    private final int secondStartY = 336;

    @Before
    public void setUp() {
        sut = new GraphicViewPresenter();
        viewSpy = new ViewSpy(sut);
        sut.setView(viewSpy);
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

                assertTrue(viewSpy.wasPaintLineCalled());
                assertActualPointEqualsExpected(firstStartX, firstStartY, viewSpy.getLastStartX(), viewSpy.getLastStartY());
                assertActualPointEqualsExpected(firstEndX, firstEndY, viewSpy.getLastEndX(), viewSpy.getLastEndY());
            }

            @Test
            public void whenLeftClickingOnce_shouldNotCallPaintObject() {
                sut.handleLeftClick(firstStartX, firstStartY);

                assertFalse(viewSpy.wasPaintLineCalled());
            }

            @Test
            public void whenLeftClickingTwice_shouldCallPaintObject() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);

                assertTrue(viewSpy.wasPaintLineCalled());
            }

            @Test
            public void whenLeftClickingTwice_paintLineShouldReceiveCorrectCoordinates() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);

                assertActualPointEqualsExpected(firstStartX, firstStartY, viewSpy.getLastStartX(), viewSpy.getLastStartY());
                assertActualPointEqualsExpected(firstEndX, firstEndY, viewSpy.getLastEndX(), viewSpy.getLastEndY());
            }

            @Test
            public void whenLeftClickingThreeTimes_shouldCallPaintLineOnce() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
                sut.handleLeftClick(secondStartX, secondStartY);

                assertEquals(1, viewSpy.getTimesPaintLineCalled());
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

                assertEquals(2, viewSpy.getTimesPaintLineCalled());
                assertActualPointEqualsExpected(firstStartX, firstStartY, viewSpy.getAllCoordinates().get(0), viewSpy.getAllCoordinates().get(1));
                assertActualPointEqualsExpected(firstEndX, firstEndY, viewSpy.getAllCoordinates().get(2), viewSpy.getAllCoordinates().get(3));
                assertActualPointEqualsExpected(firstEndX, firstEndY, viewSpy.getAllCoordinates().get(4), viewSpy.getAllCoordinates().get(5));
                assertActualPointEqualsExpected(secondStartX, secondStartY, viewSpy.getAllCoordinates().get(6), viewSpy.getAllCoordinates().get(7));
            }


            @Test
            public void whenLeftClickingThreeTimes_shouldCallPaintLineTwice() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
                sut.handleLeftClick(secondStartX, secondStartY);

                assertEquals(2, viewSpy.getTimesPaintLineCalled());
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

                assertTrue(viewSpy.wasPaintRectangleCalled());
                assertActualPointEqualsExpected(firstStartX, firstStartY, viewSpy.getLastStartX(), viewSpy.getLastStartY());
                assertActualPointEqualsExpected(firstEndX, firstEndY, viewSpy.getLastEndX(), viewSpy.getLastEndY());
            }

            @Test
            public void whenLeftClickingTwice_shouldCallPaintRectangle() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);

                assertTrue(viewSpy.wasPaintRectangleCalled());
            }

            @Test
            public void whenLeftClickingTwice_paintRectangleShouldReceiveCorrectCoordinates() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);

                assertActualPointEqualsExpected(firstStartX, firstStartY, viewSpy.getLastStartX(), viewSpy.getLastStartY());
                assertActualPointEqualsExpected(firstEndX, firstEndY, viewSpy.getLastEndX(), viewSpy.getLastEndY());
            }
        }

        public class ResetPaintingContext {
            @Test
            public void resetPaintingAcceptanceTest() {
                sut.activatePaintMode("Line");
                sut.handleLeftClick(firstStartX, firstStartY);
                sut.activatePaintMode("Line");
                sut.handleLeftClick(firstEndX, firstEndY);

                assertFalse(viewSpy.wasPaintLineCalled());
            }
        }


        public class CancelPaintingContext {
            @Test
            public void cancelPaintLineAcceptanceTest() {
                sut.activatePaintMode("Line");
                leftClickThenRightClick();
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);

                assertFalse(viewSpy.wasPaintLineCalled());
            }

            @Test
            public void cancelPaintPolyLineAcceptanceTest() {
                sut.activatePaintMode("PolyLine");
                leftClickThenRightClick();
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);

                assertFalse(viewSpy.wasPaintLineCalled());
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