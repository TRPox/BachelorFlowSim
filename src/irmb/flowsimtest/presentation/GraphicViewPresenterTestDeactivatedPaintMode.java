package irmb.flowsimtest.presentation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Created by Sven on 09.08.2016.
 */
public class GraphicViewPresenterTestDeactivatedPaintMode extends GraphicViewPresenterTestWithViewSpy {

    @Override
    public void setUp() {
        super.setUp();
        sut.deactivatePaintMode();
    }

    @Test
    public void whenLeftClickingTwice_shouldNotCallPaintObject() {
        transmitTwoPointsToPresenter(firstStartX, firstStartY, firstEndX, firstEndY);
        assertFalse(viewSpy.wasPaintObjectCalled());
    }
}
