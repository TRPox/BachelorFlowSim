package irmb.flowsimtest.presentation;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Sven on 09.08.2016.
 */
public class GraphicViewPresenterTestActivatedPaintMode extends GraphicViewPresenterTestWithViewSpy {


    @Override
    public void setUp() {
        super.setUp();
        sut.activatePaintMode();
    }

    @Test
    public void buildSingleLineAcceptanceTest() {

        transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);

        assertTrue(viewSpy.wasPaintObjectCalled());
        assertActualPointEqualsExpected(firstStartX, firstStartY, viewSpy.getStartX(), viewSpy.getStartY());
        assertActualPointEqualsExpected(firstEndX, firstEndY, viewSpy.getEndX(), viewSpy.getEndY());
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
        assertActualPointEqualsExpected(firstStartX, firstStartY, viewSpy.getStartX(), viewSpy.getStartY());
        assertActualPointEqualsExpected(firstEndX, firstEndY, viewSpy.getEndX(), viewSpy.getEndY());
    }

    @Test
    public void whenClickingMoreThanTwice_shouldCallPaintObjectOnce() {
        transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
        transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
        assertEquals(1, viewSpy.getTimesPaintObjectCalled());
    }





}
