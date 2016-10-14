package irmb.flowsim;

import irmb.flowsim.presentation.factories.ShapeFactoryImpl;
import irmb.flowsim.presentation.GraphicViewPresenter;
import irmb.flowsim.presentation.factories.ShapeBuilderFactoryImpl;
import irmb.flowsim.view.GraphicPanel;
import irmb.flowsim.view.SwingPainter;
import irmb.flowsim.view.MainWindow;

/**
 * Created by Sven on 09.08.2016.
 */
public class Main {
    public static void main(String[] args) {

        MainWindow view = new MainWindow();
        view.setSize(800, 600);
        view.setVisible(true);
        GraphicViewPresenter presenter = new GraphicViewPresenter(new ShapeBuilderFactoryImpl(new ShapeFactoryImpl()));
        presenter.setPainter(new SwingPainter(view.getGraphicPanel()));
        view.setPresenter(presenter);

    }
}
