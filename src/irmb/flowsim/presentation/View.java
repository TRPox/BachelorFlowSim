package irmb.flowsim.presentation;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Rectangle;

/**
 * Created by Sven on 09.08.2016.
 */
public interface View {
    void paintLine(Line line);

    void paintRectangle(Rectangle rectangle);

}
