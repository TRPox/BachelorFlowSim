package irmb.flowsimtest.presentation;

import irmb.flowsim.model.geometry.Line;
import irmb.flowsim.presentation.BuildLineStrategy;
import irmb.flowsimtest.model.GeometryRepositoryMock;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Sven on 01.08.2016.
 */
public class BuildLineStrategyTest {

    private BuildLineStrategy strategy;
    private GeometryRepositoryMock geometryRepositoryMock;

    @Before
    public void setUp() throws Exception {
        geometryRepositoryMock = new GeometryRepositoryMock();
        strategy = new BuildLineStrategy();
        strategy.setGeometryRepository(geometryRepositoryMock);
    }

    @Test
    public void whenExecutingFirstLeftClick_geometryRepositoryShouldReceiveLine() {
        strategy.executeLeftClick(0,0);
        assertThat(geometryRepositoryMock.receivedObject, is(instanceOf(Line.class)));
    }
    
    @Test
    public void whenExecutingFirstLeftClick_shouldCreateLineWithMatchingWorldCoordinates() {
        strategy.executeLeftClick(50,25);

    }
}