package irmb.flowsim.view;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.Painter;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Sven on 05.09.2016.
 */
public class SwingPainter extends Painter {

    private final GraphicPanel graphicPanel;
    private Graphics graphics;

    public SwingPainter(GraphicPanel graphicPanel) {
        this.graphicPanel = graphicPanel;
        this.graphics = graphicPanel.getGraphics();
    }

    @Override
    protected void paintLine(Point start, Point end) {
        graphics.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

    @Override
    protected void paintRectangle(Point start, Point end) {
        int minX = start.getX() < end.getX() ? start.getX() : end.getX();
        int minY = start.getY() < end.getY() ? start.getY() : end.getY();
        int width = Math.abs(start.getX() - end.getX());
        int height = Math.abs(start.getY() - end.getY());

        graphics.drawRect(minX, minY, width, height);
    }

    @Override
    protected void paintCircle(Point center, double radius) {
        int minX = center.getX() - (int) radius;
        int minY = center.getY() - (int) radius;
        int diameter = (int) radius * 2;
        graphics.drawOval(minX, minY, diameter, diameter);
    }

    @Override
    public void clear() {
        graphicPanel.paintComponent(graphicPanel.getGraphics());
//        try {
//            Method m = graphicPanel.getClass().getSuperclass().getSuperclass().getDeclaredMethod("paintComponent", Graphics.class);
//            m.setAccessible(true);
//            m.invoke(graphicPanel, graphicPanel.getGraphics());
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }
}
