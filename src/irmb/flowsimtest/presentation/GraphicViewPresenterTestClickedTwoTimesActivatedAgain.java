package irmb.flowsimtest.presentation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sven on 09.08.2016.
 * //
 */
public class GraphicViewPresenterTestClickedTwoTimesActivatedAgain extends GraphicViewPresenterTestActivatedPaintMode {

    private final int secondStartX = 864;
    private final int secondStartY = 965;
    private final int secondEndX = 755;
    private final int secondEndY = 851;

    //
    @Override
    public void setUp() {
        super.setUp();
        transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
        sut.activatePaintMode();
    }

    @Test
    public void buildTwoLinesAcceptanceTest() {

        transmitTwoPointsToPresenter(secondStartX, secondStartY, secondEndX, secondEndY);

        assertEquals(2, viewSpy.getTimesPaintObjectCalled());

        assertActualPointEqualsExpected(secondStartX, secondStartY, viewSpy.getStartX(), viewSpy.getStartY());
        assertActualPointEqualsExpected(secondEndX, secondEndY, viewSpy.getEndX(), viewSpy.getEndY());
    }

    @Test
    public void whenLeftTwice_paintObjectShouldReceiveCorrectCoordinates() {

        transmitTwoPointsToPresenter(secondStartX, secondStartY, secondEndX, secondEndY);

        assertActualPointEqualsExpected(secondStartX, secondStartY, viewSpy.getStartX(), viewSpy.getStartY());
        assertActualPointEqualsExpected(secondEndX, secondEndY, viewSpy.getEndX(), viewSpy.getEndY());
    }

}
