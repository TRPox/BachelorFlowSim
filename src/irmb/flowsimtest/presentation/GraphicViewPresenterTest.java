package irmb.flowsimtest.presentation;

import irmb.flowsim.model.GeometryRepository;
import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.presentation.GraphicViewPresenter;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by Sven on 27.07.2016.
 */
public class GraphicViewPresenterTest {

    private Line receivedObject;
    private GraphicViewPresenter presenter;
    private MouseStrategyFactoryStub mouseStrategyFactory;

    @Before
    public void setUp() throws Exception {
        mouseStrategyFactory = new MouseStrategyFactoryStub();
        presenter = new GraphicViewPresenter(mouseStrategyFactory);
    }

    @Test
    public void buildLineAcceptanceTest() {
        presenter.selectCreateLine();
        presenter.handleLeftClick(0, 0);
        presenter.handleLeftClick(800, 600);

        assertThat(receivedObject, is(instanceOf(Line.class)));
    }

    @Test
    public void uponConstruction_shouldRequestDefaultStrategyFromFactory() {
        assertEquals("Default", mouseStrategyFactory.requestedStrategy);
    }

    @Test
    public void whenSelectingCreateLine_shouldRequestBuildObjectStrategyFromFactory() {
        presenter.selectCreateLine();
        assertEquals("BuildLine", mouseStrategyFactory.requestedStrategy);
    }

    @Test
    public void whenLeftClicking_executeLeftClickOnMouseStrategyShouldBeCalledOnce() {
        presenter.handleLeftClick(0, 0);
        assertEquals(1, mouseStrategyFactory.strategyMock.timesExecuted);
    }

    @Test
    public void whenLeftClickingTwice_executeLeftClickOnMouseStrategyShouldBeCalledTwice() {
        presenter.handleLeftClick(0, 0);
        presenter.handleLeftClick(0, 0);
        assertEquals(2, mouseStrategyFactory.strategyMock.timesExecuted);
    }

    @Test
    public void whenLeftClicking_executeLeftClickOnMouseStrategyShouldReceiveCoordinates() {
        presenter.handleLeftClick(7, 21);
        assertEquals(7, mouseStrategyFactory.strategyMock.xValue);
        assertEquals(21, mouseStrategyFactory.strategyMock.yValue);
    }
}