package irmb.flowsim.view;

import irmb.flowsim.model.geometry.*;
import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsim.presentation.*;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Sven on 27.08.2016.
 */
public class TempPainter extends JFrame implements MouseListener {


    private GraphicViewPresenter presenter;
    private JPanel panel;
    private JButton lineButton;
    private JButton polyLineButton;
    private JButton rectangleButton;

    private Point start;
    private Point end;

    public TempPainter() {
        this.add(panel);
//        this.presenter = presenter;
        setButtonActions();
        panel.addMouseListener(this);
//        panel.addMouseMotionListener(this);
    }

    private void setButtonActions() {
        lineButton.addActionListener(e -> presenter.activatePaintMode("Line"));
        polyLineButton.addActionListener(e -> presenter.activatePaintMode("PolyLine"));
        rectangleButton.addActionListener(e -> presenter.activatePaintMode("Rectangle"));
//        circleButton.addActionListener(e -> presenter.activatePaintMode("Circle"));
    }

    public void setPresenter(GraphicViewPresenter presenter) {
        this.presenter = presenter;
    }

    private void paintLine(Point start, Point end) {
        Graphics g = getGraphics();
        if (start != null && end != null)
            g.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

    private void paintRectangle(Point start, Point end) {
        if (start != null && end != null) {
            Graphics g = getGraphics();
            int minX = start.getX() < end.getX() ? start.getX() : end.getX();
            int minY = start.getY() < end.getY() ? start.getY() : end.getY();
            int width = Math.abs(start.getX() - end.getX());
            int height = Math.abs(start.getY() - end.getY());

            g.drawRect(minX, minY, width, height);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int mouseX = e.getXOnScreen() - getX();
        int mouseY = e.getYOnScreen() - getY();

        if (e.getButton() == MouseEvent.BUTTON1) {
            presenter.handleLeftClick(mouseX, mouseY);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            presenter.handleRightClick(mouseX, mouseY);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void paint(Graphics g) {
        super.paint(g);
//        if (start != null && end != null)
//            g.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

//    @Override
//    public void visit(Line line) {
//        paintLine(line.getStart(), line.getEnd());
//    }
//
//    @Override
//    public void visit(Rectangle rectangle) {
//        paintRectangle(rectangle.getFirst(), rectangle.getSecond());
//    }
//
//    @Override
//    public void visit(PolyLine polyLine) {
//        List<Point> pointList = polyLine.getAllPoints();
//        for (int i = 0; i < pointList.size() - 1; )
//            paintLine(pointList.get(i), pointList.get(++i));
//    }
}