package irmb.flowsimtest.presentation;

import irmb.flowsim.presentation.GraphicViewPresenter;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Created by Sven on 27.07.2016.
 */
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

    protected void assertActualPointEqualsExpected(double actualX, double actualY, double expectedX, double expectedY) {
        double delta = 0.00001;
        assertEquals(actualX, expectedX, delta);
        assertEquals(actualY, expectedY, delta);
    }

    protected void transmitTwoPointsToPresenter(int startX, int startY, int endX, int endY) {
        sut.handleLeftClick(startX, startY);
        sut.handleLeftClick(endX, endY);
    }
}