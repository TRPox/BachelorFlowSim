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

    public class ActivatedPaintModeContext {
        @Before
        public void setUp() {
            sut.activatePaintMode();
        }

        @Test
        public void buildSingleLineAcceptanceTest() {

            transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);

            assertTrue(viewSpy.wasPaintObjectCalled());
            assertActualPointEqualsExpected(firstStartX, firstStartY, viewSpy.getLastStartX(), viewSpy.getLastStartY());
            assertActualPointEqualsExpected(firstEndX, firstEndY, viewSpy.getLastEndX(), viewSpy.getLastEndY());
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


        public class ClickedTwoTimesPaintModeActivatedAgainContext {

            private final int secondStartX = 864;
            private final int secondStartY = 965;
            private final int secondEndX = 755;
            private final int secondEndY = 851;

            @Before
            public void setUp() {
                transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
                sut.activatePaintMode();
            }

            @Test
            public void buildTwoLinesAcceptanceTest() {

                transmitTwoPointsToPresenter(secondStartX, secondStartY, secondEndX, secondEndY);

                assertEquals(2, viewSpy.getTimesPaintObjectCalled());
                assertActualPointEqualsExpected(secondStartX, secondStartY, viewSpy.getLastStartX(), viewSpy.getLastStartY());
                assertActualPointEqualsExpected(secondEndX, secondEndY, viewSpy.getLastEndX(), viewSpy.getLastEndY());

            }

            @Test
            public void whenLeftClickingTwice_paintObjectShouldReceiveCorrectCoordinates() {

                transmitTwoPointsToPresenter(secondStartX, secondStartY, secondEndX, secondEndY);

                assertActualPointEqualsExpected(secondStartX, secondStartY, viewSpy.getLastStartX(), viewSpy.getLastStartY());
                assertActualPointEqualsExpected(secondEndX, secondEndY, viewSpy.getLastEndX(), viewSpy.getLastEndY());
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
    }


}