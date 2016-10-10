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

import java.util.List;

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
                    assertActualPointEqualsExpected(painterSpy.getSecondReceived(), second);

                    sut.handleLeftClick(third.getX(), third.getY());
                    assertEquals(2, painterSpy.getTimesPaintLineCalled());
                    assertActualPointEqualsExpected(painterSpy.getFirstReceived(), first);
                    assertActualPointEqualsExpected(painterSpy.getSecondReceived(), third);
                }

                @Test
                public void whenMovingMouseBeforeLeftClick_shouldNotCallPaintLine() {
                    sut.handleMouseMove(first.getX(), first.getY());

                    assertFalse(painterSpy.wasPaintLineCalled());
                }

                @Test
                public void whenMovingMouseAfterLeftClick_shouldCallPaintLine() {
                    sut.handleLeftClick(first.getX(), first.getY());
                    sut.handleMouseMove(second.getX(), second.getY());

                    assertTrue(painterSpy.wasPaintLineCalled());
                }

                @Test
                public void whenMovingMouseAfterLeftClick_shouldCallPaintLineWithCorrectCoordinates() {
                    sut.handleLeftClick(first.getX(), first.getY());
                    sut.handleMouseMove(second.getX(), second.getY());

                    assertActualPointEqualsExpected(painterSpy.getFirstReceived(), first);
                    assertActualPointEqualsExpected(painterSpy.getSecondReceived(), second);
                }

                @Test
                public void whenMovingMouseTwice_shouldCallPaintLineWithAdjustedLastPoint() {
                    sut.handleLeftClick(first.getX(), first.getY());

                    sut.handleMouseMove(second.getX(), second.getY());
                    assertActualPointEqualsExpected(painterSpy.getSecondReceived(), second);

                    sut.handleMouseMove(third.getX(), third.getY());

                    assertActualPointEqualsExpected(painterSpy.getFirstReceived(), first);
                    assertActualPointEqualsExpected(painterSpy.getSecondReceived(), third);
                }

                @Test
                public void whenBuildingCompleteLineWithMouseMove_shouldCallPaintLineWithCorrectCoordinates() {
                    sut.handleLeftClick(first.getX(), first.getY());
                    sut.handleMouseMove(second.getX(), second.getY());
                    sut.handleLeftClick(third.getX(), third.getY());

                    assertActualPointEqualsExpected(painterSpy.getFirstReceived(), first);
                    assertActualPointEqualsExpected(painterSpy.getSecondReceived(), third);
                }

                @Test
                public void whenMovingMouseAfterCompletingLine_shouldNotCallPaintLine() {
                    sut.handleLeftClick(first.getX(), first.getY());
                    sut.handleLeftClick(second.getX(), second.getY());
                    sut.handleMouseMove(third.getX(), third.getY());

                    assertEquals(1, painterSpy.getTimesPaintLineCalled());
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


                assertEquals(2, painterSpy.getTimesPaintLineCalled());
                Point firstReceived = painterSpy.getReceivedPointList().get(0);
                Point secondReceived = painterSpy.getReceivedPointList().get(1);
                Point thirdReceived = painterSpy.getReceivedPointList().get(2);
                Point fourthReceived = painterSpy.getReceivedPointList().get(3);
                assertActualPointEqualsExpected(firstReceived, first);
                assertActualPointEqualsExpected(secondReceived, second);
                assertActualPointEqualsExpected(thirdReceived, second);
                assertActualPointEqualsExpected(fourthReceived, third);
            }


            @Test
            public void whenLeftClickingThreeTimes_shouldCallPaintLineTwice() {
                transmitTwoPointsToPresenter(first, second);
                sut.handleLeftClick(third.getX(), third.getY());

                assertEquals(2, painterSpy.getTimesPaintLineCalled());
            }

//            public class LivePaintingPolyLineContext {
//                @Test
//                public void livePaintingPolyLineAcceptanceTest() {
//                    Point fourth = new Point(86, 33);
//                    Point fifth = new Point(511, 355);
//
//                    sut.handleLeftClick(first.getX(), first.getY());
//                    sut.handleMouseMove(second.getX(), second.getY());
//                    sut.handleLeftClick(third.getX(), third.getY());
//                    sut.handleMouseMove(fourth.getX(), fourth.getY());
//                    sut.handleLeftClick(fifth.getX(), fifth.getY());
//
//                    assertEquals(4, painterSpy.getTimesPaintLineCalled());
//                    List<Point> pointList = painterSpy.getReceivedPointList();
//                    assertActualPointEqualsExpected(pointList.get(0), first);
//                    assertActualPointEqualsExpected(pointList.get(1), third);
//                    assertActualPointEqualsExpected(pointList.get(2), fifth);
//                }
//            }

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
                    sut.handleLeftClick(third.getX(), third.getY());

                    assertEquals(2, painterSpy.getTimesPaintRectangleCalled());
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
                    sut.handleLeftClick(third.getX(), third.getY());

                    assertEquals(2, painterSpy.getTimesPaintCircleCalled());
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