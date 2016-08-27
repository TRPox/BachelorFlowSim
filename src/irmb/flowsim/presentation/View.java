package irmb.flowsim.presentation;

import irmb.flowsim.model.geometry.Point;

/**
 * Created by Sven on 09.08.2016.
 */
public interface View {
    void paintLine(Point start, Point end);

    void paintRectangle(Point start, Point end);
}
