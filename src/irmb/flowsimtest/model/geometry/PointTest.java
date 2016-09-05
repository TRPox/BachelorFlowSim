package irmb.flowsimtest.model.geometry;

import irmb.flowsim.model.geometry.Point;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sven on 05.09.2016.
 */
public class PointTest {

    private final double delta = 0.000001;

    @Test
    public void testDistanceTo() {

        assertEquals(5, makePoint(0, 3).distanceTo(makePoint(4, 0)), delta);
        assertEquals(10, makePoint(0, 6).distanceTo(makePoint(8, 0)), delta);
    }

    private Point makePoint(int x, int y) {
        return new Point(x, y);
    }
}