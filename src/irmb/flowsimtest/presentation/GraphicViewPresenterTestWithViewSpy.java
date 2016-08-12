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

    protected GraphicViewPresenter sut;
    protected ViewSpy viewSpy;

    protected final int firstStartX = 564;
    protected final int firstStartY = 321;
    protected final int firstEndX = 741;
    protected final int firstEndY = 1123;

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

    private void assertTwoPointsTransmittedToView() {
        assertTrue(viewSpy.wasPaintObjectCalled());
        assertActualPointEqualsExpected(firstStartX, firstStartY, viewSpy.getLastStartX(), viewSpy.getLastStartY());
        assertActualPointEqualsExpected(firstEndX, firstEndY, viewSpy.getLastEndX(), viewSpy.getLastEndY());
        assertEquals(4, viewSpy.getAllCoordinates().size());
    }

    public class ActivatedPaintModeContext {

        public class InvalidObjectTypeContext {

            @Test
            public void whenGivenEmptyStringAndLeftClickingTwice_shouldNotCallPaintObject() {
                sut.activatePaintMode("");
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
                assertFalse(viewSpy.wasPaintObjectCalled());
            }

            @Test
            public void whenGivenInvalidStringAndLeftClickingTwice_shouldNotCallPaintObject() {
                sut.activatePaintMode("Invalid");
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
                assertFalse(viewSpy.wasPaintObjectCalled());
            }
        }

        public class BuildLineContext {

            @Before
            public void setUp() {
                sut.activatePaintMode("Line");
            }

            @Test
            public void buildSingleLineAcceptanceTest() {

                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);

                assertTwoPointsTransmittedToView();
            }


            @Test
            public void whenLeftClickingTwice_shouldCallPaintObject() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
                assertTrue(viewSpy.wasPaintObjectCalled());
            }

            @Test
            public void whenLeftClickingOnce_shouldNotCallPaintObject() {
                sut.handleLeftClick(800, 600);
                assertFalse(viewSpy.wasPaintObjectCalled());
            }


            @Test
            public void whenLeftClickingTwice_paintObjectShouldReceiveCorrectCoordinates() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
                assertActualPointEqualsExpected(firstStartX, firstStartY, viewSpy.getLastStartX(), viewSpy.getLastStartY());
                assertActualPointEqualsExpected(firstEndX, firstEndY, viewSpy.getLastEndX(), viewSpy.getLastEndY());
            }

            @Test
            public void whenClickingMoreThanTwice_shouldCallPaintObjectOnce() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
                assertEquals(1, viewSpy.getTimesPaintObjectCalled());
            }

            @Test
            public void whenRightClicking_shouldNotCallPaintObject() {
                sut.handleRightClick(firstStartX, firstStartY);
                assertFalse(viewSpy.wasPaintObjectCalled());
            }

            @Test
            public void whenLeftClickingOnce_RightClickingOnce_LeftClickingOnce_shouldNotCallPaintObject() {
                sut.handleLeftClick(firstStartX, firstStartY);
                sut.handleRightClick(firstStartX, firstStartY);
                sut.handleLeftClick(firstEndX, firstEndY);
                assertFalse(viewSpy.wasPaintObjectCalled());
            }

            public class BuildMultipleLinesContext {

                private final int secondStartX = 864;
                private final int secondStartY = 965;
                private final int secondEndX = 755;
                private final int secondEndY = 851;

                @Before
                public void setUp() {
                    transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
                    sut.activatePaintMode("Line");
                }

                @Test
                public void buildTwoLinesAcceptanceTest() {

                    transmitTwoPointsToPresenter(secondStartX, secondStartY, secondEndX, secondEndY);

                    assertEquals(2, viewSpy.getTimesPaintObjectCalled());
                    assertActualPointEqualsExpected(secondStartX, secondStartY, viewSpy.getLastStartX(), viewSpy.getLastStartY());
                    assertActualPointEqualsExpected(secondEndX, secondEndY, viewSpy.getLastEndX(), viewSpy.getLastEndY());

                }

                @Test
                public void whenLeftClickingTwice_paintObjectShouldNotReceiveOldCoordinates() {
                    transmitTwoPointsToPresenter(secondStartX, secondStartY, secondEndX, secondEndY);

                    assertFalse(viewSpy.getAllCoordinates().contains(firstStartX));
                    assertFalse(viewSpy.getAllCoordinates().contains(firstStartY));
                    assertFalse(viewSpy.getAllCoordinates().contains(firstEndX));
                    assertFalse(viewSpy.getAllCoordinates().contains(firstEndY));
                }

                @Test
                public void whenLeftClickingTwice_paintObjectShouldReceiveCorrectCoordinates() {

                    transmitTwoPointsToPresenter(secondStartX, secondStartY, secondEndX, secondEndY);

                    assertActualPointEqualsExpected(secondStartX, secondStartY, viewSpy.getLastStartX(), viewSpy.getLastStartY());
                    assertActualPointEqualsExpected(secondEndX, secondEndY, viewSpy.getLastEndX(), viewSpy.getLastEndY());
                }

            }
        }

        public class BuildPolyLineContext {

            private final int secondStartX = 864;
            private final int secondStartY = 965;
            private final int secondEndX = 755;
            private final int secondEndY = 851;

            @Before
            public void setUp() {
                sut.activatePaintMode("PolyLine");
            }

            @Test
            public void buildPolyLineAcceptanceTest() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
                transmitTwoPointsToPresenter(secondStartX, secondStartY, secondEndX, secondEndY);
                sut.handleRightClick(secondEndX, secondEndY);

                assertEquals(1, viewSpy.getTimesPaintObjectCalled());
                assertActualPointEqualsExpected(firstStartX, firstStartY, viewSpy.getAllCoordinates().get(0), viewSpy.getAllCoordinates().get(1));
                assertActualPointEqualsExpected(firstEndX, firstEndY, viewSpy.getAllCoordinates().get(2), viewSpy.getAllCoordinates().get(3));
                assertActualPointEqualsExpected(secondStartX, secondStartY, viewSpy.getAllCoordinates().get(4), viewSpy.getAllCoordinates().get(5));
                assertActualPointEqualsExpected(secondEndX, secondEndY, viewSpy.getAllCoordinates().get(6), viewSpy.getAllCoordinates().get(7));
            }

            @Test
            public void whenRightClickingBeforeLeftClick_shouldNotCallPaintObject() {
                sut.handleRightClick(firstStartX, firstStartY);
                assertFalse(viewSpy.wasPaintObjectCalled());
            }

            @Test
            public void whenRightClickingAfterLeftClickingOnce_shouldNotCallPaintObject() {
                sut.handleLeftClick(firstStartX, firstStartY);
                sut.handleRightClick(firstStartX, firstStartY);
                assertFalse(viewSpy.wasPaintObjectCalled());
            }

            @Test
            public void whenLeftClickingTwice_shouldNotCallPaintObject() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);

                assertFalse(viewSpy.wasPaintObjectCalled());
            }

            @Test
            public void whenLeftClickingFourTimesAndRightClickingOnce_shouldCallPaintObjectOnce() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
                transmitTwoPointsToPresenter(secondStartX, secondStartY, secondEndX, secondEndY);
                sut.handleRightClick(secondEndX, secondEndY);

                assertEquals(1, viewSpy.getTimesPaintObjectCalled());
            }

            @Test
            public void whenLeftClickingFourTimesAndRightClickingOnce_paintObjectShouldReceiveCorrectCoordinates() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
                transmitTwoPointsToPresenter(secondStartX, secondStartY, secondEndX, secondEndY);
                sut.handleRightClick(secondEndX, secondEndY);

                assertEquals(8, viewSpy.getAllCoordinates().size());
                assertActualPointEqualsExpected(firstStartX, firstStartY, viewSpy.getAllCoordinates().get(0), viewSpy.getAllCoordinates().get(1));
                assertActualPointEqualsExpected(firstEndX, firstEndY, viewSpy.getAllCoordinates().get(2), viewSpy.getAllCoordinates().get(3));
                assertActualPointEqualsExpected(secondStartX, secondStartY, viewSpy.getAllCoordinates().get(4), viewSpy.getAllCoordinates().get(5));
                assertActualPointEqualsExpected(secondEndX, secondEndY, viewSpy.getAllCoordinates().get(6), viewSpy.getAllCoordinates().get(7));
            }

            @Test
            public void whenRightClickingSecondTime_paintObjectShouldOnlyBeCalledOnce() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
                sut.handleRightClick(secondEndX, secondEndY);
                sut.handleRightClick(secondEndX, secondEndY);
                assertEquals(1, viewSpy.getTimesPaintObjectCalled());
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
                assertTwoPointsTransmittedToView();
                assertEquals("Rectangle", viewSpy.getObjectString());
            }
        }

    }



    public class DeactivatedPaintModeContext {

        @Before
        public void setUp() {
            sut.deactivatePaintMode();
        }

        @Test
        public void whenLeftClickingTwice_shouldNotCallPaintObject() {
            transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
            assertFalse(viewSpy.wasPaintObjectCalled());
        }

        @Test
        public void whenRightClicking_shouldNotCallPaintObject() {
            sut.handleRightClick(firstStartX, firstStartY);
            assertFalse(viewSpy.wasPaintObjectCalled());
        }
    }


}