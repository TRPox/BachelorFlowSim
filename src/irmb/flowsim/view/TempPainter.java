package irmb.flowsim.view;

import irmb.flowsim.model.geometry.*;
import irmb.flowsim.model.geometry.Shape;
import irmb.flowsim.model.geometry.Point;
import irmb.flowsim.model.geometry.Rectangle;
import irmb.flowsim.presentation.*;

import java.awt.event.MouseMotionListener;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Sven on 27.08.2016.
 */
public class TempPainter extends JFrame implements MouseListener, MouseMotionListener {


    private GraphicViewPresenter presenter;
    private JPanel panel;
    private JButton lineButton;
    private JButton polyLineButton;
    private JButton rectangleButton;
    private JButton circleButton;

    private Point start;
    private Point end;

    public TempPainter() {
        this.add(panel);
//        this.presenter = presenter;
        setButtonActions();
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
    }

    private void setButtonActions() {
        lineButton.addActionListener(e -> presenter.activatePaintMode("Line"));
        polyLineButton.addActionListener(e -> presenter.activatePaintMode("PolyLine"));
        rectangleButton.addActionListener(e -> presenter.activatePaintMode("Rectangle"));
        circleButton.addActionListener(e -> presenter.activatePaintMode("Circle"));
    }

    public void setPresenter(GraphicViewPresenter presenter) {
        this.presenter = presenter;
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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getXOnScreen() - getX();
        int mouseY = e.getYOnScreen() - getY();
        presenter.handleMouseMove(mouseX, mouseY);
    }
}
