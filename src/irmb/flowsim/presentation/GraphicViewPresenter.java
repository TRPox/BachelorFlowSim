package irmb.flowsim.presentation;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.builders.ShapeBuilder;
import irmb.flowsim.presentation.factories.ShapeBuilderFactory;

/**
 * Created by Sven on 27.07.2016.
 */
public class GraphicViewPresenter {

    private Painter painter;
    private int pointsAdded;
    private boolean paintEnabled;
    private ShapeBuilderFactory shapeBuilderFactory;
    private ShapeBuilder shapeBuilder;

    public GraphicViewPresenter(ShapeBuilderFactory shapeBuilderFactory) {
        this.shapeBuilderFactory = shapeBuilderFactory;
    }

    public void handleLeftClick(int x, int y) {
        pointsAdded++;
        if (paintEnabled) {
            paintShapes(x, y);
        }
    }

    private void paintShapes(int x, int y) {
        shapeBuilder.addPoint(makePoint(x, y));
        if (pointsAdded >= 2) {
            shapeBuilder.getShape().accept(painter);
            if (shapeBuilder.isObjectFinished())
                deactivatePaintMode();
        }
    }

    private Point makePoint(int x, int y) {
        return new Point(x, y);
    }

    public void handleRightClick(int x, int y) {
        paintEnabled = false;
        painter.clear();
    }

    public void handleMouseMove(int x, int y) {
        if (paintEnabled && pointsAdded > 0) {
            painter.clear();
            if (pointsAdded > 1)
                shapeBuilder.setLastPoint(makePoint(x, y));
            else {
                shapeBuilder.addPoint(makePoint(x, y));
                pointsAdded++;
            }
            shapeBuilder.getShape().accept(painter);
        }
    }

    public void activatePaintMode(String type) {
        pointsAdded = 0;
        paintEnabled = true;
        shapeBuilder = shapeBuilderFactory.makeShapeBuilder(type);
    }

    public void setPainter(Painter painter) {
        this.painter = painter;
    }

    public void deactivatePaintMode() {
        paintEnabled = false;
    }


}
