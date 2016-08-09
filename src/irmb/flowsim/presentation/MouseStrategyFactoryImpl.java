package irmb.flowsim.presentation;

/**
 * Created by Sven on 31.07.2016.
 */
public class MouseStrategyFactoryImpl implements MouseStrategyFactory {
    @Override
    public MouseStrategy createStrategy(String strategy) {
        return new BuildLineStrategy();
    }
}
