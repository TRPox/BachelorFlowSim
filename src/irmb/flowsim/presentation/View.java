package irmb.flowsim.presentation;

import java.util.List;

/**
 * Created by Sven on 09.08.2016.
 */
public interface View {
    void paintObject(String objectType, List<Integer> coordinates);
}
