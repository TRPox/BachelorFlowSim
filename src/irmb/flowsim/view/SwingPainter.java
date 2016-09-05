package irmb.flowsim.view;

import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.presentation.Painter;

import java.awt.*;

/**
 * Created by Sven on 05.09.2016.
 */
public class SwingPainter extends Painter {

    private final Graphics graphics;

    public SwingPainter(Graphics graphics) {
        this.graphics = graphics;
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
}
