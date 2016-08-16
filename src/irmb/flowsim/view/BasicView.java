package irmb.flowsim.view;

import irmb.flowsim.presentation.GraphicViewPresenter;
import irmb.flowsim.presentation.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sven on 09.08.2016.
 */
public class BasicView extends JFrame implements View, MouseListener, MouseMotionListener {
    private JButton lineButton;
    private JPanel panel;
    private JButton polyLineButton;
    private JButton rectangleButton;
    private JButton circleButton;
    private GraphicViewPresenter presenter;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private List<Integer> paintList = new ArrayList<>();
    private String objectType = "";

    public BasicView(GraphicViewPresenter presenter) {
        this.add(panel);
        this.presenter = presenter;
        setButtonActions();
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
    }

    public void setPresenter(GraphicViewPresenter presenter) {
        this.presenter = presenter;
        setButtonActions();
    }

    private void setButtonActions() {
        lineButton.addActionListener(e -> presenter.activatePaintMode("Line"));
        polyLineButton.addActionListener(e -> presenter.activatePaintMode("PolyLine"));
        rectangleButton.addActionListener(e -> presenter.activatePaintMode("Rectangle"));
        circleButton.addActionListener(e -> presenter.activatePaintMode("Circle"));
    }

    @Override
    public void paintObject(String objectType, List<Integer> coordinates) {
        paintList.clear();
        paintList.addAll(coordinates);
        System.out.println(paintList);
        this.objectType = objectType;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (objectType.equals("Rectangle")) {
            if (paintList.size() == 4) {
                int minX = paintList.get(0) < paintList.get(2) ? paintList.get(0) : paintList.get(2);
                int minY = paintList.get(1) < paintList.get(3) ? paintList.get(1) : paintList.get(3);
                int width = Math.abs(paintList.get(2) - paintList.get(0));
                int height = Math.abs(paintList.get(3) - paintList.get(1));
                g.drawRect(minX, minY, width, height);
            }
        } else if (objectType.equals("Circle")) {
            int width = Math.abs(paintList.get(2) - paintList.get(0));
            int height = Math.abs(paintList.get(3) - paintList.get(1));
            int radius = (int) Math.sqrt(height * height + width * width);
            g.drawOval(paintList.get(0) - radius, paintList.get(1) - radius, radius * 2, radius * 2);
        } else {
            for (int i = 0; i < paintList.size() - 3; ) {
                g.drawLine(paintList.get(i++), paintList.get(i++), paintList.get(i++), paintList.get(i++));
                i -= 2;
            }
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
        if (e.getButton() == MouseEvent.BUTTON1) {
            presenter.handleLeftClick(e.getXOnScreen() - this.getX(), e.getYOnScreen() - this.getY());
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            presenter.handleRightClick(e.getXOnScreen() - this.getX(), e.getYOnScreen() - this.getY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        presenter.handleMouseMove(e.getXOnScreen() - this.getX(), e.getYOnScreen() - this.getY());
    }
}
