package tech.otter.intrepid;

import com.artemis.World;
import tech.otter.intrepid.ui.BaseScreen;

/**
 * @author John Lynn <john@otter.tech>
 * @date 3/11/18
 */
public interface Controller {
    void exit();
    void changeScreen(BaseScreen screen);
    World getModel();
}
