package irmb.flowsim;

import irmb.flowsim.presentation.GraphicViewPresenter;
import irmb.flowsim.presentation.factories.ShapeFactoryImpl;
import irmb.flowsim.view.TempView;

/**
 * Created by Sven on 09.08.2016.
 */
public class Main {
    public static void main(String[] args) {

        TempView view = new TempView();
        view.setSize(800, 600);
        view.setVisible(true);
        GraphicViewPresenter presenter = new GraphicViewPresenter(new ShapeFactoryImpl());
        presenter.setView(view);
        view.setPresenter(presenter);
    }
}
