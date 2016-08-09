package irmb.flowsimtest.presentation;

import irmb.flowsim.presentation.MouseStrategy;
import irmb.flowsim.presentation.MouseStrategyFactory;

/**
 * Created by Sven on 28.07.2016.
 */
public class MouseStrategyFactoryStub implements MouseStrategyFactory {
    public String requestedStrategy;
    public MouseStrategyStub strategyMock;


    @Override
    public MouseStrategy createStrategy(String strategy) {
        requestedStrategy = strategy;
        strategyMock = new MouseStrategyStub();
        return strategyMock;
    }
}
