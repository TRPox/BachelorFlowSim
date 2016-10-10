package irmb.flowsim.presentation;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.builders.ShapeBuilder;
import irmb.flowsim.presentation.factories.ShapeBuilderFactory;

/**
 * Created by Sven on 27.07.2016.
 */
public class GraphicViewPresenter {

    private Painter painter;
    private int timesCalled;
    private String objectType;
    private boolean paintEnabled;
    private ShapeBuilderFactory shapeBuilderFactory;
    private ShapeBuilder shapeBuilder;
    private boolean mouseMoved;

    public GraphicViewPresenter(ShapeBuilderFactory shapeBuilderFactory) {
        this.shapeBuilderFactory = shapeBuilderFactory;
    }

    public void handleLeftClick(int x, int y) {
        timesCalled++;
        if (paintEnabled) {
            paintShapes(x, y);
        }
    }

    private void paintShapes(int x, int y) {
        shapeBuilder.addPoint(makePoint(x, y));
        if (timesCalled >= 2) {
            shapeBuilder.setLastPoint(makePoint(x, y));
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
    }

    public void activatePaintMode(String type) {
        objectType = type;
        timesCalled = 0;
        paintEnabled = true;
        shapeBuilder = shapeBuilderFactory.makeShapeBuilder(objectType);
    }

    public void setPainter(Painter painter) {
        this.painter = painter;
    }

    public void deactivatePaintMode() {
        paintEnabled = false;
    }


    public void handleMouseMove(int x, int y) {
        if (!mouseMoved) {
            shapeBuilder.addPoint(makePoint(x, y));
            mouseMoved = true;
        } else
            shapeBuilder.setLastPoint(makePoint(x, y));
        if (paintEnabled)
            if (timesCalled > 0)
                shapeBuilder.getShape().accept(painter);
    }
}
