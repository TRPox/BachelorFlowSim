package irmb.flowsimtest.presentation;

import irmb.flowsim.presentation.MouseStrategy;

/**
 * Created by Sven on 29.07.2016.
 */
public class MouseStrategyStub implements MouseStrategy {

    public int timesExecuted = 0;
    public int xValue;
    public int yValue;

    @Override
    public void executeLeftClick(int x, int y) {
        timesExecuted++;
        xValue = x;
        yValue = y;
    }
}
