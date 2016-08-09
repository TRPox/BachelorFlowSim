package irmb.flowsim.presentation;

import irmb.flowsim.model.GeometryRepository;
import irmb.flowsim.model.geometry.Line;
import irmb.flowsimtest.model.GeometryRepositoryMock;

/**
 * Created by Sven on 31.07.2016.
 */
public class BuildLineStrategy implements MouseStrategy {
    private GeometryRepository geometryRepository;

    @Override
    public void executeLeftClick(int x, int y) {
        geometryRepository.add(new Line() {});
    }

    public void setGeometryRepository(GeometryRepository geometryRepository) {
        this.geometryRepository = geometryRepository;
    }
}
