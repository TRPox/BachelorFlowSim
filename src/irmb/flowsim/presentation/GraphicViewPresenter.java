package irmb.flowsim.presentation;

import irmb.flowsim.model.GeometryRepository;

/**
 * Created by Sven on 27.07.2016.
 */
public class GraphicViewPresenter {
    private MouseStrategyFactory mouseStrategyFactory;
    private MouseStrategy strategy;

    public GraphicViewPresenter(MouseStrategyFactory factory) {
        mouseStrategyFactory = factory;
        strategy = mouseStrategyFactory.createStrategy("Default");
    }

    public void selectCreateLine() {
        mouseStrategyFactory.createStrategy("BuildLine");
    }

    public void handleLeftClick(int x, int y) {
        strategy.executeLeftClick(x, y);
    }

    public void setMouseStrategyFactory(MouseStrategyFactory mouseStrategyFactory) {
        this.mouseStrategyFactory = mouseStrategyFactory;
    }
}
