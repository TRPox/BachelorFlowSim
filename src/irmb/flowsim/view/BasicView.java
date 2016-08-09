package irmb.flowsim.view;

import irmb.flowsim.presentation.GraphicViewPresenter;
import irmb.flowsim.presentation.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Sven on 09.08.2016.
 */
public class BasicView extends JFrame implements View, MouseListener{
    private JButton linieButton;
    private JPanel panel;
    private GraphicViewPresenter presenter;
    private double startX;
    private double startY;
    private double endX;
    private double endY;


    public BasicView(GraphicViewPresenter presenter) {
        this.add(panel);
        this.presenter = presenter;
        linieButton.addActionListener(e -> presenter.activatePaintMode());
        panel.addMouseListener(this);
    }

    public void setPresenter(GraphicViewPresenter presenter) {
        this.presenter = presenter;
        linieButton.addActionListener(e -> presenter.activatePaintMode());
    }

    @Override
    public void paintObject(double startX, double startY, double endX, double endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawLine((int)startX, (int)startY, (int)endX, (int)endY);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getXOnScreen() ;
        presenter.handleLeftClick(e.getXOnScreen() - this.getX(), e.getYOnScreen() - this.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
}
