package irmb.flowsim.view;

import irmb.flowsim.presentation.GraphicViewPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Sven on 11.10.2016.
 */
public class GraphicPanel extends JPanel implements MouseListener, MouseMotionListener {
    private GraphicViewPresenter presenter;

    public GraphicPanel() {
        setDoubleBuffered(true);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        if (e.getButton() == MouseEvent.BUTTON1) {
            presenter.handleLeftClick(mouseX, mouseY);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            presenter.handleRightClick(mouseX, mouseY);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

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
        int mouseX = e.getX();
        int mouseY = e.getY();
        presenter.handleMouseMove(mouseX, mouseY);
    }

    public void setPresenter(GraphicViewPresenter presenter) {
        this.presenter = presenter;
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
    }

}
