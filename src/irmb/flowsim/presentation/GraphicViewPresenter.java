package irmb.flowsim.presentation;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.presentation.factories.ShapeFactory;

/**
 * Created by Sven on 27.07.2016.
 */
public class GraphicViewPresenter {

    private View view;
    private int timesCalled;
    private Point lastPoint;
    private String objectType;
    private boolean paintEnabled;
    private ShapeFactory shapeFactory;

    public GraphicViewPresenter(ShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    public void handleLeftClick(int x, int y) {
        timesCalled++;
        if (paintEnabled) {
            paintShapes(x, y);
            lastPoint = new Point(x, y);
        }
    }

    private void paintShapes(int x, int y) {
        Shape shape = shapeFactory.makeShape(objectType);
        if (objectType.equals("Line")) {
            if (timesCalled == 2) {
                Line line = (Line) shape;
                line.setStart(lastPoint);
                line.setEnd(new Point(x, y));
                view.paintLine(line);
            }
        } else if (objectType.equals("Rectangle")) {
            if (timesCalled == 2) {
                Rectangle rectangle = (Rectangle) shape;
                rectangle.setFirst(lastPoint);
                rectangle.setSecond(new Point(x, y));
                view.paintRectangle(rectangle);
            }
        } else {
            if (timesCalled >= 2) {
                Line line = (Line) shape;
                line.setStart(lastPoint);
                line.setEnd(new Point(x, y));
                view.paintLine(line);
            }
        }
    }

    public void handleRightClick(int x, int y) {
        paintEnabled = false;
    }

    public void activatePaintMode(String type) {
        objectType = type;
        timesCalled = 0;
        paintEnabled = true;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void deactivatePaintMode() {

    }


}
