package irmb.flowsimtest.presentation;

import irmb.flowsim.presentation.BuildLineStrategy;
import irmb.flowsim.presentation.MouseStrategyFactory;
import irmb.flowsim.presentation.MouseStrategyFactoryImpl;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

/**
 * Created by Sven on 31.07.2016.
 */
public class MouseStrategyFactoryImplTest {

    @Test
    public void canCreateMouseStrategyFactory() {
        MouseStrategyFactory factory = new MouseStrategyFactoryImpl();
    }

    @Test
    public void whenCalledWithBuildLine_shouldReturnBuildLineStrategy() {
        MouseStrategyFactory factory = new MouseStrategyFactoryImpl();
        assertThat(factory.createStrategy("BuildLine"), is(instanceOf(BuildLineStrategy.class)));
    }

}