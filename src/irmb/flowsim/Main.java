package irmb.flowsim;

import irmb.flowsim.presentation.GraphicViewPresenter;
import irmb.flowsim.view.BasicView;

/**
 * Created by Sven on 09.08.2016.
 */
public class Main {
    public static void main(String[] args) {
        GraphicViewPresenter presenter = new GraphicViewPresenter();
        BasicView view = new BasicView(presenter);
        presenter.setView(view);
//        sut.activatePaintMode();
        view.setSize(800, 600);
        view.setVisible(true);
//        presenter.handleLeftClick(50,50);
//        presenter.handleLeftClick(750,650);



    }
}
