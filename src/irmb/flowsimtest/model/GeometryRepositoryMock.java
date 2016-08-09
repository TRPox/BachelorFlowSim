package irmb.flowsimtest.model;

import irmb.flowsim.model.GeometryRepository;
import irmb.flowsim.model.geometry.Line;

/**
 * Created by Sven on 01.08.2016.
 */
public class GeometryRepositoryMock implements GeometryRepository {

    public Line receivedObject;

    @Override
    public void add(Line line) {
        receivedObject = line;
    }
}
