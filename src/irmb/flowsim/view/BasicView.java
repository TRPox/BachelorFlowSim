package irmb.flowsim.view;

import irmb.flowsim.presentation.GraphicViewPresenter;
import irmb.flowsim.presentation.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sven on 09.08.2016.
 */
public class BasicView extends JFrame implements View, MouseListener {
    private JButton lineButton;
    private JPanel panel;
    private JButton polyLineButton;
    private GraphicViewPresenter presenter;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private List<Integer> paintList = new ArrayList<>();

    public BasicView(GraphicViewPresenter presenter) {
        this.add(panel);
        this.presenter = presenter;
        lineButton.addActionListener(e -> presenter.activatePaintMode("Line"));
        polyLineButton.addActionListener(e -> presenter.activatePaintMode("PolyLine"));
        panel.addMouseListener(this);
    }

    public void setPresenter(GraphicViewPresenter presenter) {
        this.presenter = presenter;
        lineButton.addActionListener(e -> presenter.activatePaintMode("Line"));
        polyLineButton.addActionListener(e -> presenter.activatePaintMode("PolyLine"));
    }

    @Override
    public void paintObject(List<Integer> coordinates) {
        paintList.clear();
        paintList.addAll(coordinates);
        System.out.println(paintList);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < paintList.size() - 3; ) {
            g.drawLine(paintList.get(i++), paintList.get(i++), paintList.get(i++), paintList.get(i++));
            i-=2;
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
        int x = e.getXOnScreen();
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

}
