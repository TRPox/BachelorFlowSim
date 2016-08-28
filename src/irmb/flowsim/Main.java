package irmb.flowsim;

import irmb.flowsim.presentation.GraphicViewPresenter;
import irmb.flowsim.view.TempView;

/**
 * Created by Sven on 09.08.2016.
 */
public class Main {
    public static void main(String[] args) {
        GraphicViewPresenter presenter = new GraphicViewPresenter();
        TempView view = new TempView(presenter);
        presenter.setView(view);
        view.setSize(800, 600);
        view.setVisible(true);
    }
}
