package irmb.flowsimtest.presentation;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.GraphicViewPresenter;
import irmb.flowsim.presentation.factories.ShapeBuilderFactoryImpl;
import irmb.flowsim.presentation.factories.ShapeFactory;
import irmb.flowsim.presentation.factories.ShapeFactoryImpl;
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
public class GraphicViewPresenterTestWithPainterSpy {

    private final double delta = 0.000001;

    private GraphicViewPresenter sut;
    private PainterSpy painterSpy;

    @Before
    public void setUp() {
        ShapeFactory shapeFactory = new ShapeFactoryImpl();
        ShapeBuilderFactoryImpl shapeBuilderFactory = new ShapeBuilderFactoryImpl(shapeFactory);

        sut = new GraphicViewPresenter(shapeBuilderFactory);

        painterSpy = new PainterSpy();
        sut.setPainter(painterSpy);
    }

    private void assertActualPointEqualsExpected(Point actual, Point expected) {
        assertEquals(expected.getX(), actual.getX(), delta);
        assertEquals(expected.getY(), actual.getY(), delta);
    }

    private void transmitTwoPointsToPresenter(Point first, Point second) {
        sut.handleLeftClick(first.getX(), first.getY());
        sut.handleLeftClick(second.getX(), second.getY());
    }

    public class ActivatedPaintModeContext {

        private final Point first = new Point(564, 321);
        private final Point second = new Point(741, 1123);
        private final Point third = new Point(631, 336);

        public class BuildLineContext {

            @Before
            public void setUp() {
                sut.activatePaintMode("Line");
            }

            @Test
            public void buildLineAcceptanceTest() {
                transmitTwoPointsToPresenter(first, second);

                assertTrue(painterSpy.wasPaintLineCalled());
                assertActualPointEqualsExpected(painterSpy.getFirstReceived(), first);
                assertActualPointEqualsExpected(painterSpy.getSecondReceived(), second);
            }

            @Test
            public void whenLeftClickingOnce_shouldNotCallPaintObject() {
                sut.handleLeftClick(first.getX(), first.getY());

                assertFalse(painterSpy.wasPaintLineCalled());
            }

            @Test
            public void whenLeftClickingTwice_shouldCallPaintObject() {
                transmitTwoPointsToPresenter(first, second);

                assertTrue(painterSpy.wasPaintLineCalled());
            }

            @Test
            public void whenLeftClickingTwice_paintLineShouldReceiveCorrectCoordinates() {
                transmitTwoPointsToPresenter(first, second);

                assertActualPointEqualsExpected(painterSpy.getFirstReceived(), first);
                assertActualPointEqualsExpected(painterSpy.getSecondReceived(), second);
            }

            @Test
            public void whenLeftClickingThreeTimes_shouldCallPaintLineOnce() {
                transmitTwoPointsToPresenter(first, second);

                sut.handleLeftClick(third.getX(), third.getY());

                assertEquals(1, painterSpy.getTimesPaintLineCalled());
            }

            public class LivePaintingLineContext {

                @Test
                public void livePaintingLineAcceptanceTest() {
                    sut.handleLeftClick(first.getX(), first.getY());

                    sut.handleMouseMove(second.getX(), second.getY());
                    assertTrue(painterSpy.wasPaintLineCalled());
                    assertTrue(painterSpy.wasClearCalled());
                    assertActualPointEqualsExpected(painterSpy.getFirstReceived(), first);
                    assertActualPointEqualsExpected(painterSpy.getSecondReceived(), second);

                    sut.handleMouseMove(third.getX(), third.getY());
                    assertEquals(2, painterSpy.getTimesPaintLineCalled());
                    assertEquals(2, painterSpy.getTimesClearCalled());
                    assertActualPointEqualsExpected(painterSpy.getSecondReceived(), third);

                    sut.handleLeftClick(third.getX(), third.getY());
                    assertActualPointEqualsExpected(painterSpy.getFirstReceived(), first);
                    assertActualPointEqualsExpected(painterSpy.getSecondReceived(), third);
                    assertEquals(3, painterSpy.getTimesPaintLineCalled());
                }

                @Test
                public void whenMovingMouseBeforeLeftClick_shouldNotCallClearOrPaintLine() {
                    sut.handleMouseMove(first.getX(), first.getY());
                    assertFalse(painterSpy.wasClearCalled());
                    assertFalse(painterSpy.wasPaintLineCalled());
                }

                public class LeftClickedOnceContext {
                    @Before
                    public void setUp() {
                        sut.handleLeftClick(first.getX(), first.getY());
                    }

                    @Test
                    public void whenMovingMouse_shouldCallClear() {
                        sut.handleMouseMove(second.getX(), second.getY());
                        assertTrue(painterSpy.wasClearCalled());
                    }

                    @Test
                    public void whenMovingMouse_shouldCallPaintLine() {
                        sut.handleMouseMove(second.getX(), second.getY());
                        assertTrue(painterSpy.wasPaintLineCalled());
                    }

                    @Test
                    public void whenMovingMouse_shouldAddNewPoint() {
                        sut.handleMouseMove(second.getX(), second.getY());
                        assertActualPointEqualsExpected(painterSpy.getSecondReceived(), second);
                    }

                    @Test
                    public void whenMovingMouseTwice_shouldAdjustLastPoint() {
                        sut.handleMouseMove(second.getX(), second.getY());
                        sut.handleMouseMove(third.getX(), third.getY());
                        assertActualPointEqualsExpected(painterSpy.getSecondReceived(), third);
                    }

                    @Test
                    public void whenMovingMouseAndLeftClicking_paintLineShouldReceiveCorrectCoordinates() {
                        sut.handleMouseMove(second.getX(), second.getY());
                        sut.handleLeftClick(second.getX(), second.getY());

                        assertActualPointEqualsExpected(painterSpy.getFirstReceived(), first);
                        assertActualPointEqualsExpected(painterSpy.getSecondReceived(), second);
                    }

                    public class LeftClickedTwiceContext {
                        @Before
                        public void setUp() {
                            sut.handleLeftClick(second.getX(), second.getY());
                        }

                        @Test
                        public void whenMovingMouse_shouldNotAdjustLastPoint() {
                            sut.handleMouseMove(third.getX(), third.getY());

                            assertActualPointEqualsExpected(painterSpy.getSecondReceived(), second);
                        }

                        @Test
                        public void whenMovingMouse_shouldNotCallPaintLineAgain() {
                            sut.handleMouseMove(third.getX(), third.getY());

                            assertEquals(1, painterSpy.getTimesPaintLineCalled());
                        }
                    }

                }

            }
        }

        public class BuildPolyLineContext {

            @Before
            public void setUp() {
                sut.activatePaintMode("PolyLine");
            }

            @Test
            public void buildPolyLineAcceptanceTest() {
                transmitTwoPointsToPresenter(first, second);
                sut.handleLeftClick(third.getX(), third.getY());


                assertEquals(3, painterSpy.getTimesPaintLineCalled());
                Point firstReceived = painterSpy.getReceivedPointList().get(0);
                Point secondReceived = painterSpy.getReceivedPointList().get(1);
                Point thirdReceived = painterSpy.getReceivedPointList().get(2);
                Point fourthReceived = painterSpy.getReceivedPointList().get(3);
                Point fifthReceived = painterSpy.getReceivedPointList().get(4);
                Point sixthReceived = painterSpy.getReceivedPointList().get(5);
                assertActualPointEqualsExpected(firstReceived, first);
                assertActualPointEqualsExpected(secondReceived, second);
                assertActualPointEqualsExpected(thirdReceived, first);
                assertActualPointEqualsExpected(fourthReceived, second);
                assertActualPointEqualsExpected(fifthReceived, second);
                assertActualPointEqualsExpected(sixthReceived, third);
            }


            @Test
            public void whenLeftClickingThreeTimes_shouldCallPaintLineThreeTimes() {
                transmitTwoPointsToPresenter(first, second);
                sut.handleLeftClick(third.getX(), third.getY());

                assertEquals(3, painterSpy.getTimesPaintLineCalled());
            }

            public class LivePaintingPolyLineContext {
                @Test
                public void livePaintingPolyLineAcceptanceTest() {

                    sut.handleLeftClick(first.getX(), first.getY());

                    sut.handleMouseMove(second.getX(), second.getY());
                    assertActualPointEqualsExpected(painterSpy.getReceivedPointList().get(0), first);
                    assertActualPointEqualsExpected(painterSpy.getReceivedPointList().get(1), second);
                    assertTrue(painterSpy.wasClearCalled());
                    assertEquals(1, painterSpy.getTimesPaintLineCalled());

                    sut.handleLeftClick(second.getX(), second.getY());
                    assertActualPointEqualsExpected(painterSpy.getReceivedPointList().get(2), first);
                    assertActualPointEqualsExpected(painterSpy.getReceivedPointList().get(3), second);
                    assertActualPointEqualsExpected(painterSpy.getReceivedPointList().get(4), second);
                    assertActualPointEqualsExpected(painterSpy.getReceivedPointList().get(5), second);
                    assertEquals(3, painterSpy.getTimesPaintLineCalled());

                    sut.handleMouseMove(third.getX(), third.getY());
                    assertActualPointEqualsExpected(painterSpy.getReceivedPointList().get(6), first);
                    assertActualPointEqualsExpected(painterSpy.getReceivedPointList().get(7), second);
                    assertActualPointEqualsExpected(painterSpy.getReceivedPointList().get(8), second);
                    assertActualPointEqualsExpected(painterSpy.getReceivedPointList().get(9), third);
                    assertEquals(2, painterSpy.getTimesClearCalled());
                    assertEquals(5, painterSpy.getTimesPaintLineCalled());
                }
            }

        }

        public class BuildRectangleContext {
            @Before
            public void setUp() {
                sut.activatePaintMode("Rectangle");
            }

            @Test
            public void buildRectangleAcceptanceTest() {
                transmitTwoPointsToPresenter(first, second);

                assertTrue(painterSpy.wasPaintRectangleCalled());
                assertActualPointEqualsExpected(painterSpy.getFirstReceived(), first);
                assertActualPointEqualsExpected(painterSpy.getSecondReceived(), second);
            }

            @Test
            public void whenLeftClickingTwice_shouldCallPaintRectangle() {
                transmitTwoPointsToPresenter(first, second);

                assertTrue(painterSpy.wasPaintRectangleCalled());
            }

            @Test
            public void whenLeftClickingTwice_paintRectangleShouldReceiveCorrectCoordinates() {
                transmitTwoPointsToPresenter(first, second);

                assertActualPointEqualsExpected(painterSpy.getFirstReceived(), first);
                assertActualPointEqualsExpected(painterSpy.getSecondReceived(), second);
            }

            public class LivePaintingRectangleContext {
                @Test
                public void livePaintingRectangleAcceptanceTest() {
                    sut.handleLeftClick(first.getX(), first.getY());

                    sut.handleMouseMove(second.getX(), second.getY());
                    assertTrue(painterSpy.wasPaintRectangleCalled());
                    assertTrue(painterSpy.wasClearCalled());
                    assertActualPointEqualsExpected(painterSpy.getFirstReceived(), first);
                    assertActualPointEqualsExpected(painterSpy.getSecondReceived(), second);

                    sut.handleMouseMove(third.getX(), third.getY());
                    assertEquals(2, painterSpy.getTimesPaintRectangleCalled());
                    assertEquals(2, painterSpy.getTimesClearCalled());
                    assertActualPointEqualsExpected(painterSpy.getSecondReceived(), third);


                    sut.handleLeftClick(third.getX(), third.getY());
                    assertEquals(3, painterSpy.getTimesPaintRectangleCalled());
                    assertActualPointEqualsExpected(painterSpy.getFirstReceived(), first);
                    assertActualPointEqualsExpected(painterSpy.getSecondReceived(), third);
                }
            }
        }

        public class BuildCircleContext {
            @Before
            public void setUp() {
                sut.activatePaintMode("Circle");
            }

            @Test
            public void buildCircleAcceptanceTest() {
                transmitTwoPointsToPresenter(first, second);

                assertTrue(painterSpy.wasPaintCircleCalled());
                assertActualPointEqualsExpected(painterSpy.getFirstReceived(), first);
                double distance = getExpectedRadius();
                assertEquals(distance, painterSpy.getReceivedRadius(), delta);
            }

            private double getExpectedRadius() {
                return first.distanceTo(second);
            }

            public class LivePaintingCircleContext {
                @Test
                public void livePaintingCircleAcceptanceTest() {
                    sut.handleLeftClick(first.getX(), first.getY());

                    sut.handleMouseMove(second.getX(), second.getY());
                    assertTrue(painterSpy.wasPaintCircleCalled());
                    assertTrue(painterSpy.wasClearCalled());
                    assertEquals(first.distanceTo(second), painterSpy.getReceivedRadius(), delta);

                    sut.handleMouseMove(third.getX(), third.getY());
                    assertEquals(2, painterSpy.getTimesPaintCircleCalled());
                    assertEquals(2, painterSpy.getTimesClearCalled());
                    assertActualPointEqualsExpected(painterSpy.getFirstReceived(), first);
                    assertEquals(first.distanceTo(third), painterSpy.getReceivedRadius(), delta);

                    sut.handleLeftClick(third.getX(), third.getY());
                    assertActualPointEqualsExpected(painterSpy.getFirstReceived(), first);
                    assertEquals(first.distanceTo(third), painterSpy.getReceivedRadius(), delta);
                }
            }
        }

        public class ResetPaintingContext {
            @Test
            public void resetPaintingAcceptanceTest() {
                sut.activatePaintMode("Line");
                sut.handleLeftClick(first.getX(), first.getY());
                sut.activatePaintMode("Line");
                sut.handleLeftClick(second.getX(), second.getY());

                assertFalse(painterSpy.wasPaintLineCalled());
            }
        }

        public class CancelPaintingContext {
            @Test
            public void cancelPaintLineAcceptanceTest() {
                sut.activatePaintMode("Line");
                leftClickThenRightClick();
                transmitTwoPointsToPresenter(first, second);

                assertFalse(painterSpy.wasPaintLineCalled());
            }

            @Test
            public void cancelPaintPolyLineAcceptanceTest() {
                sut.activatePaintMode("PolyLine");
                leftClickThenRightClick();
                transmitTwoPointsToPresenter(first, second);

                assertFalse(painterSpy.wasPaintLineCalled());
            }

            private void leftClickThenRightClick() {
                sut.handleLeftClick(first.getX(), first.getY());
                sut.handleRightClick(0, 0);
            }
        }
    }


}